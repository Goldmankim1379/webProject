package com.spring.web.board.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.vo.boardVO;

@Mapper
public interface BoardMapper {

	public List<boardVO>getList(boardVO searchVO);

	public void insertBoard(boardVO searchVO);

	public boardVO getBoardContents(int board_idx);

	public void updateBoard(boardVO searchVO);

	public void getBoardDelete(int board_idx);

	public int getListCnt(boardVO searchVO); 
}