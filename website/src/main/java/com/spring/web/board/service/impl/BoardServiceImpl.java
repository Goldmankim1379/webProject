package com.spring.web.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.board.service.BoardService;
import com.spring.web.vo.boardVO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;

	@Override
	public List<boardVO> getList(boardVO searchVO) {
		 return mapper.getList(searchVO); 
	}

	@Override
	public void insertBoard(boardVO searchVO) {
		mapper.insertBoard(searchVO);
	}}