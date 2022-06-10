package com.spring.web.vo;

import java.io.UnsupportedEncodingException;

public class boardVO extends PageVO {
	
	private int board_idx;
    private String board_title;
    private String board_content;
    private String board_writer;
    private String board_regdate;
    private String board_updatedate;
    
    private String qustr;
    
    
    public void setQustr() throws UnsupportedEncodingException {
		
    	String qs = "";
    	this.setQueryString();
    	qs += this.getQueryString();
    	this.qustr = qs;
	}
    
    
    public String getQustr() {
		return qustr;
	}
	public int getBoard_idx() {
        return board_idx;
    }
    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }
    public String getBoard_title() {
        return board_title;
    }
    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }
    public String getBoard_content() {
        return board_content;
    }
    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }
    public String getBoard_writer() {
        return board_writer;
    }
    public void setBoard_writer(String board_writer) {
        this.board_writer = board_writer;
    }
    public String getBoard_regdate() {
        return board_regdate;
    }
    public void setBoard_regdate(String board_regdate) {
        this.board_regdate = board_regdate;
    }
    public String getBoard_updatedate() {
        return board_updatedate;
    }
    public void setBoard_updatedate(String board_updatedate) {
        this.board_updatedate = board_updatedate;
    }
    
}