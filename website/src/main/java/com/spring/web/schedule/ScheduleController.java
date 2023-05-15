package com.spring.web.schedule;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.spring.web.schedule.service.ScheduleService;
import com.spring.web.vo.boardVO;
import com.spring.web.vo.scheduleVO;

import net.sf.json.JSONObject;

@Controller
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(value = "/schedule/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("searchVO") scheduleVO searchVO, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
	    if(null != inputFlashMap) {
	    	
	    	model.addAttribute("msg",(String) inputFlashMap.get("msg"));
	    	
	    }
	    
		
		
		return "/schedule/list";
	}
	
	@RequestMapping(value = "/schedule/list_ajax", method = RequestMethod.GET)
	public String list_ajax(@ModelAttribute("searchVO") scheduleVO searchVO, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if(null != inputFlashMap) {
			
			model.addAttribute("msg",(String) inputFlashMap.get("msg"));
			
		}
		
		
		
		return "/schedule/list";
	}
	
	
	@RequestMapping(value = "/schedule/list_ajax", method = RequestMethod.POST)
	@ResponseBody
	public String list_ajax(
			scheduleVO searchVO,
    		HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JSONObject obj = new JSONObject();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{

	    	List<scheduleVO> mainScheduleList = scheduleService.getMainScheduleList(searchVO);		// 예약 리스트
			obj.put("mainScheduleList", mainScheduleList);
			out.print(obj);
			
		}catch(Exception e){
			System.out.println(e.toString());
    	}
		
		return null;
	}
	
//	
//	@RequestMapping(value = "/schedule/create", method = RequestMethod.GET)
//	public String create(@ModelAttribute("searchVO") boardVO searchVO, Model model) {
//		
//		
//		return "/schedule/create";
//	}
//	
//	@RequestMapping(value = "/schedule/create_action", method = RequestMethod.POST)
//	public String create_action(@ModelAttribute("searchVO") boardVO searchVO, HttpServletRequest request, RedirectAttributes redirect){
//		
//		try {
//			
//			SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
//			Date time = new Date();
//			String time1 = format1.format(time);
//			searchVO.setBoard_regdate(time1);
//			
//			boardService.insertBoard(searchVO);
//			
//			redirect.addFlashAttribute("redirect", searchVO.getBoard_idx());
//			redirect.addFlashAttribute("msg", "등록이 완료되었습니다.");
//			
//		} catch (Exception e) {
//			
//			redirect.addFlashAttribute("msg", "오류가 발생되었습니다.");
//			
//		}
//		
//		return "redirect:/schedule/list";
//	}
//	
//	@RequestMapping(value = "/schedule/read", method = RequestMethod.GET)
//	public String read(@ModelAttribute("searchVO") boardVO searchVO, @RequestParam("board_idx") int board_idx, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
//		
//		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
//	    if(null != inputFlashMap) {
//	    	
//	    	model.addAttribute("msg",(String) inputFlashMap.get("msg"));
//	    	
//	    }
//		
//		boardVO boardContents = boardService.getBoardContents(board_idx);
//		model.addAttribute("boardContents", boardContents);
//		
//		searchVO.setQustr();
//		
//		return "/schedule/read";
//	}
//	
//	@RequestMapping(value = "/schedule/update", method = RequestMethod.GET)
//	public String update(@ModelAttribute("searchVO") boardVO searchVO, @RequestParam("board_idx") int board_idx, Model model) throws UnsupportedEncodingException {
//		
//		boardVO boardContents = boardService.getBoardContents(board_idx);
//		model.addAttribute("boardContents", boardContents);
//		
//		searchVO.setQustr();
//		
//		return "/schedule/update";
//	}
//	
//	
//	@RequestMapping(value = "/schedule/update_action", method = RequestMethod.POST)
//	public String update_action(@ModelAttribute("searchVO") boardVO searchVO, HttpServletRequest request, RedirectAttributes redirect , Model model) throws UnsupportedEncodingException{
//		
//		
//		try {
//		
//		boardService.updateBoard(searchVO);
//		redirect.addFlashAttribute("redirect", searchVO.getBoard_idx());
//		redirect.addFlashAttribute("redirect", searchVO.getQustr());
//		
//		redirect.addFlashAttribute("msg", "수정이 완료되었습니다.");
//			
//		} catch (Exception e) {
//		
//		redirect.addFlashAttribute("msg", "오류가 발생되었습니다.");
//		
//		}
//		
//		searchVO.setQustr();
//		
//		return "redirect:/schedule/read?board_idx=" + searchVO.getBoard_idx() + "&" + searchVO.getQustr();
//	}
//	
//	@RequestMapping(value = "/schedule/delete", method = RequestMethod.GET)
//	public String delete(@ModelAttribute("searchVO") boardVO searchVO, @RequestParam("board_idx") int board_idx, RedirectAttributes redirect , Model model) throws UnsupportedEncodingException {
//		
//		try {
//			
//			boardService.getBoardDelete(board_idx);
//			redirect.addFlashAttribute("msg", "삭제가 완료되었습니다.");
//			
//		} catch (Exception e) {
//			redirect.addFlashAttribute("msg", "오류가 발생되었습니다.");
//			
//		}
//		
//		searchVO.setQustr();
//		
//		return "redirect:/schedule/list?" + searchVO.getQustr();
//	}
//	

}