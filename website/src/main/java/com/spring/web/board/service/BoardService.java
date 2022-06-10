package com.spring.web.board.service;

import java.util.List;

import com.spring.web.vo.boardVO;

public interface BoardService {

	List<boardVO> getList(boardVO searchVO);

	void insertBoard(boardVO searchVO);

	boardVO getBoardContents(int board_idx);

	void updateBoard(boardVO searchVO);

	void getBoardDelete(int board_idx);

	int getListCnt(boardVO searchVO);

}