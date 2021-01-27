package com.spring.web.board.service;

import java.util.List;

import com.spring.web.vo.boardVO;

public interface BoardService {

	List<boardVO> getList(boardVO searchVO);

	void insertBoard(boardVO searchVO);

}