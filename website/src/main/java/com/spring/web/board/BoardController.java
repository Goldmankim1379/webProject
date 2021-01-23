package com.spring.web.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.web.board.service.BoardService;
import com.spring.web.vo.boardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("searchVO") boardVO searchVO, Model model) {
		
		
		List<boardVO> boardList = boardService.getList(searchVO);
		
		model.addAttribute("boardList",boardList);
		
		return "/board/list";
	}
	
	
	@RequestMapping(value = "/board/create", method = RequestMethod.GET)
	public String create(@ModelAttribute("searchVO") boardVO searchVO, Model model) {
		
		
		return "/board/create";
	}
	

}