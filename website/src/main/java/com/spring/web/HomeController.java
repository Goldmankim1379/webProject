package com.spring.web;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.spring.web.account.service.AccountService;
import com.spring.web.vo.LoginVO;
import com.spring.web.vo.memberVO;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private AccountService accountService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
		
		
		
		try {
		
			
			LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
			
			if(loginVO != null) {
				memberVO searchVO = new memberVO();
				searchVO.setMe_id(loginVO.getId());
				memberVO memberSearch = accountService.memberInfoSearch(searchVO);
				
				model.addAttribute("searchVO", memberSearch);
				model.addAttribute("loginVO",loginVO);
			}
			
			
			Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		    if(null != inputFlashMap) {
		    	
		    	model.addAttribute("msg",(String) inputFlashMap.get("msg"));
		    }
	    	
	    
		} catch (Exception e) {
			
		}
		
		
		return "home";
	}
	
	
}
