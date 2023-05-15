package com.spring.web.account.web;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.web.account.service.AccountService;
import com.spring.web.sso.CookieUtil;
import com.spring.web.sso.JwtUtil;
import com.spring.web.vo.LoginVO;
import com.spring.web.vo.memberVO;

import net.sf.json.JSONObject;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/account/register", method = RequestMethod.GET)
	public String register() {
		
		return "/account/register";
	}
	
	
	@RequestMapping(value = "/account/getIdCnt", method = RequestMethod.POST)
	@ResponseBody
	public String loginCntReset(@RequestBody String filterJSON, HttpServletResponse response, ModelMap model) throws Exception {

		JSONObject resMap = new JSONObject();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			memberVO searchVO = (memberVO) mapper.readValue(filterJSON, new TypeReference<memberVO>() {
			});
			
			int idCnt = accountService.getIdCnt(searchVO);
			
			resMap.put("res", "ok");
			resMap.put("idCnt", idCnt);

		} catch (Exception e) {
			System.out.println(e.toString());
			resMap.put("res", "error");
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resMap);

		return null;
	}
	
	
	
	@RequestMapping(value = "/account/register_action", method = RequestMethod.POST)
	public String register_action(@ModelAttribute("searchVO") memberVO searchVO, HttpServletRequest request, RedirectAttributes redirect){
		
		try {
			
			SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
			Date time = new Date();
			String time1 = format1.format(time);
			searchVO.setMe_regdate(time1);
			String encrypt = encryptPassword(searchVO.getMe_pwd().trim());
			searchVO.setMe_pwd(encrypt);
			
			accountService.insertMember(searchVO);
			
			redirect.addFlashAttribute("msg", "회원가입이 완료되었습니다.");
			
		} catch (Exception e) {
			
			redirect.addFlashAttribute("msg", "오류가 발생되었습니다.");
			
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/account/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model,
			memberVO searchVO, @CookieValue(value="CustomCheck", required=false) Cookie rememberCookie) {
		
		if(rememberCookie!=null) {
			model.addAttribute("rememberCookie", rememberCookie);
        }
		
		return "/account/login";
	}
	
	
	@RequestMapping(value = "/account/logout" , method = RequestMethod.GET)
    public String logout(
    		HttpServletRequest request,
    		SessionStatus status,
    		HttpSession session,
    		HttpServletResponse httpServletResponse,
    		ModelMap model)  throws Exception {
		
		Object URL = session.getAttribute("URL");

		//로그인 정보 조회
		status.setComplete();
    	session.removeAttribute("loginVO");
    	session.invalidate();

    	CookieUtil.clear(httpServletResponse, "GBJWTTCRBASE");
		
		String requestURL = "/";
		if(StringUtils.isNotBlank((String) URL)) requestURL = (String)URL;

		return "redirect:"+requestURL;
    }
	
		//로그인 실행
		@RequestMapping(value = "/account/login_action", method = RequestMethod.POST)
	    public String actionLogin(
	    		@ModelAttribute("searchVO") memberVO searchVO,
	    		HttpServletRequest request,
	    		HttpSession session,
	    		RedirectAttributes redirectAttributes,
	    		HttpServletResponse httpServletResponse,
	    		ModelMap model) throws Exception {
			

			String encrypt = encryptPassword(searchVO.getMe_pwd().trim());
			searchVO.setMe_pwd(encrypt);
			
			memberVO loginInfo = accountService.actionLogin(searchVO);
			
			if(loginInfo == null){
				redirectAttributes.addFlashAttribute("msg", "로그인을 실폐하였습니다.");
				return "redirect:/account/login";
			}
			
			Cookie rememberCookie = new Cookie("CustomCheck", loginInfo.getMe_id());
            rememberCookie.setPath("/");
            if(searchVO.isCustomCheck()) {
                rememberCookie.setMaxAge(60*60*24*7);
            } else {
                rememberCookie.setMaxAge(0);
            }
            httpServletResponse.addCookie(rememberCookie);
            
			
			LoginVO loginVO = new LoginVO();
			loginVO.setId(loginInfo.getMe_id());
			loginVO.setName(loginInfo.getMe_name());
			loginVO.setPwd(loginInfo.getMe_pwd());
			loginVO.setAuth(loginInfo.getMe_auth());
			loginVO.setEmail(loginInfo.getMe_email());
			loginVO.setTel(loginInfo.getMe_tel());
			loginVO.setLatest_login(loginInfo.getMe_latest_login());
			loginVO.setRegdate(loginInfo.getMe_regdate());

			session.setAttribute("loginVO", loginVO);
			String token = JwtUtil.generateToken("GBWEBSITE", loginVO);
			CookieUtil.create(httpServletResponse, "GBJWTTCRBASE", token, false, -1);		

			accountService.updateLastLogin(searchVO);
			Object URL = session.getAttribute("URL");

			
			String requestURL = "/";
			if(StringUtils.isNotBlank((String) URL)) requestURL = (String)URL;

			return "redirect:"+requestURL;
		}
		
		
		public static String encryptPassword(String data) throws Exception {

			if (data == null) {
				return "";
			}

			byte[] plainText = null; // 평문
			byte[] hashValue = null; // 해쉬값
			plainText = data.getBytes();

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			hashValue = md.digest(plainText);

			return new String(Base64.encodeBase64(hashValue));
		}
		
		
		
		@RequestMapping(value = "/account/search_id", method = RequestMethod.GET)
		public String search_id(HttpServletRequest request, Model model,
				memberVO searchVO) {
			
			
			return "/account/search_id";
		}
		
		@RequestMapping(value = "/account/search_result_id")
		public String search_result_id(HttpServletRequest request, Model model,
				@RequestParam(required = true, value = "me_name") String me_name, 
				@RequestParam(required = true, value = "me_tel") String me_tel,
				memberVO searchVO) {
			
			
			try {
				
				searchVO.setMe_name(me_name);
				searchVO.setMe_tel(me_tel);
				memberVO memberSearch = accountService.memberIdSearch(searchVO);
				
				model.addAttribute("searchVO", memberSearch);

			} catch (Exception e) {
				System.out.println(e.toString());
				model.addAttribute("msg", "오류가 발생되었습니다.");
			}
			
			return "/account/search_result_id";
		}
		
		
		@RequestMapping(value = "/account/search_pwd", method = RequestMethod.GET)
		public String search_pwd(HttpServletRequest request, Model model,
				memberVO searchVO) {
			
			
			return "/account/search_pwd";
		}
		
		@RequestMapping(value = "/account/search_result_pwd", method = RequestMethod.POST)
		public String search_result_pwd(HttpServletRequest request, Model model,
				@RequestParam(required = true, value = "me_name") String me_name, 
				@RequestParam(required = true, value = "me_tel") String me_tel, 
				@RequestParam(required = true, value = "me_id") String me_id, 
				memberVO searchVO) {
			
			try {
				
				searchVO.setMe_name(me_name);
				searchVO.setMe_tel(me_tel);
				searchVO.setMe_id(me_id);
				int memberSearch = accountService.memberPwdCheck(searchVO);
				
				if(memberSearch == 0) {
					model.addAttribute("msg", "기입된 정보가 잘못되었습니다. 다시 입력해주세요.");
					return "/account/search_pwd";
				}
				
				String newPwd = RandomStringUtils.randomAlphanumeric(10);
				String enpassword = encryptPassword(newPwd);
				searchVO.setMe_pwd(enpassword);
				
				accountService.passwordUpdate(searchVO);
				
				model.addAttribute("newPwd", newPwd);

			} catch (Exception e) {
				System.out.println(e.toString());
				model.addAttribute("msg", "오류가 발생되었습니다.");
			}
			
			
			return "/account/search_result_pwd";
		}
		
		
		
		@RequestMapping(value = "/account/profile")
		public String profile(HttpServletRequest request, Model model,HttpSession session,
				memberVO searchVO) {
			
			try {
				
				LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
				
				searchVO.setMe_id(loginVO.getId());
				memberVO memberSearch = accountService.memberInfoSearch(searchVO);
				
				model.addAttribute("searchVO", memberSearch);

			} catch (Exception e) {
				System.out.println(e.toString());
				model.addAttribute("msg", "오류가 발생되었습니다.");
			}
			
			return "/account/profile";
		}
		
		
		@RequestMapping(value = "/account/profile_action", method = RequestMethod.POST)
		public String profile_action(HttpServletRequest request, RedirectAttributes redirectAttributes,Model model,HttpSession session,
				memberVO searchVO) {
			
			try {
				accountService.memberUpdate(searchVO);
				redirectAttributes.addFlashAttribute("msg", "수정하였습니다.");
				
			} catch (Exception e) {
				System.out.println(e.toString());
				redirectAttributes.addFlashAttribute("msg", "오류가 발생되었습니다.");
			}
			
			return "redirect:/account/profile";
		}
		
		
		
	

}