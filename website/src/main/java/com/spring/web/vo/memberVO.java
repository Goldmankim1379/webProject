package com.spring.web.vo;

import java.io.UnsupportedEncodingException;

public class memberVO extends PageVO {
	
	private int me_idx;
    private String me_name;
    private String me_id;
    private String me_pwd;
    private String me_auth;
    private String me_email;
    private String me_tel;
    private String me_latest_login;
    private String me_delete_yn;
    private String me_id_yn;
    private String me_regdate;
    private boolean customCheck;
    
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
	public int getMe_idx() {
		return me_idx;
	}
	public void setMe_idx(int me_idx) {
		this.me_idx = me_idx;
	}
	public String getMe_name() {
		return me_name;
	}
	public void setMe_name(String me_name) {
		this.me_name = me_name;
	}
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	public String getMe_pwd() {
		return me_pwd;
	}
	public void setMe_pwd(String me_pwd) {
		this.me_pwd = me_pwd;
	}
	public String getMe_auth() {
		return me_auth;
	}
	public void setMe_auth(String me_auth) {
		this.me_auth = me_auth;
	}
	public String getMe_email() {
		return me_email;
	}
	public void setMe_email(String me_email) {
		this.me_email = me_email;
	}
	public String getMe_tel() {
		return me_tel;
	}
	public void setMe_tel(String me_tel) {
		this.me_tel = me_tel;
	}
	public String getMe_latest_login() {
		return me_latest_login;
	}
	public void setMe_latest_login(String me_latest_login) {
		this.me_latest_login = me_latest_login;
	}
	public String getMe_delete_yn() {
		return me_delete_yn;
	}
	public void setMe_delete_yn(String me_delete_yn) {
		this.me_delete_yn = me_delete_yn;
	}
	public String getMe_id_yn() {
		return me_id_yn;
	}
	public void setMe_id_yn(String me_id_yn) {
		this.me_id_yn = me_id_yn;
	}
	public String getMe_regdate() {
		return me_regdate;
	}
	public void setMe_regdate(String me_regdate) {
		this.me_regdate = me_regdate;
	}

	public boolean isCustomCheck() {
		return customCheck;
	}

	public void setCustomCheck(boolean customCheck) {
		this.customCheck = customCheck;
	}
	
}