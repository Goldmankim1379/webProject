package com.spring.web.account.service.impl;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.vo.LoginVO;
import com.spring.web.vo.memberVO;

@Mapper
public interface AccountMapper {

	int getIdCnt(memberVO searchVO);

	void insertMember(memberVO searchVO);

	memberVO actionLogin(memberVO searchVO);

	void updateLastLogin(memberVO searchVO);

	memberVO memberIdSearch(memberVO searchVO);

	int memberPwdCheck(memberVO searchVO);

	void passwordUpdate(memberVO searchVO);

	void memberPasswordUpdate(memberVO searchVO);

	void memberUpdate(memberVO searchVO);

	memberVO memberInfoSearch(memberVO searchVO);

}