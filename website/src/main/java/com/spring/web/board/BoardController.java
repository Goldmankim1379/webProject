package com.spring.web.board;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.spring.web.board.service.BoardService;
import com.spring.web.vo.boardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("searchVO") boardVO searchVO, HttpServletRequest request, Model model) {
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
	    if(null != inputFlashMap) {
	    	
	    	model.addAttribute("msg",(String) inputFlashMap.get("msg"));
	    	
	    }
		
		List<boardVO> boardList = boardService.getList(searchVO);
		
		model.addAttribute("boardList",boardList);
		
		return "/board/list";
	}
	
	
	@RequestMapping(value = "/board/create", method = RequestMethod.GET)
	public String create(@ModelAttribute("searchVO") boardVO searchVO, Model model) {
		
		
		return "/board/create";
	}
	
	@RequestMapping(value = "/board/create_action", method = RequestMethod.POST)
	public String create_action(@ModelAttribute("searchVO") boardVO searchVO, HttpServletRequest request, RedirectAttributes redirect){
		
		try {
			
			SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
			Date time = new Date();
			String time1 = format1.format(time);
			searchVO.setBoard_regdate(time1);
			
			boardService.insertBoard(searchVO);
			
			redirect.addFlashAttribute("redirect", searchVO.getBoard_idx());
			redirect.addFlashAttribute("msg", "등록이 완료되었습니다.");
			
		} catch (Exception e) {
			
			redirect.addFlashAttribute("msg", "오류가 발생되었습니다.");
			
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	public String read(@ModelAttribute("searchVO") boardVO searchVO, @RequestParam("board_idx") int board_idx, Model model, HttpServletRequest request) {
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
	    if(null != inputFlashMap) {
	    	
	    	model.addAttribute("msg",(String) inputFlashMap.get("msg"));
	    	
	    }
		
		boardVO boardContents = boardService.getBoardContents(board_idx);
		model.addAttribute("boardContents", boardContents);
		
		return "/board/read";
	}
	
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(@ModelAttribute("searchVO") boardVO searchVO, @RequestParam("board_idx") int board_idx, Model model) {
		
		boardVO boardContents = boardService.getBoardContents(board_idx);
		model.addAttribute("boardContents", boardContents);
		
		return "/board/update";
	}
	
	
	@RequestMapping(value = "/board/update_action", method = RequestMethod.POST)
	public String update_action(@ModelAttribute("searchVO") boardVO searchVO, HttpServletRequest request, RedirectAttributes redirect , Model model){
		
		
		try {
		
		boardService.updateBoard(searchVO);
		redirect.addFlashAttribute("redirect", searchVO.getBoard_idx());
		
		redirect.addFlashAttribute("msg", "수정이 완료되었습니다.");
			
		} catch (Exception e) {
		
		redirect.addFlashAttribute("msg", "오류가 발생되었습니다.");
		
		}
		
		return "redirect:/board/read?board_idx=" + searchVO.getBoard_idx();
	}
	
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute("searchVO") boardVO searchVO, @RequestParam("board_idx") int board_idx, RedirectAttributes redirect , Model model) {
		
		try {
			
			boardService.getBoardDelete(board_idx);
			redirect.addFlashAttribute("msg", "삭제가 완료되었습니다.");
			
		} catch (Exception e) {
			redirect.addFlashAttribute("msg", "오류가 발생되었습니다.");
			
		}
		
		return "redirect:/board/list";
	}
	

}