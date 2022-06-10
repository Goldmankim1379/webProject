package com.spring.web.account.service;

import com.spring.web.vo.LoginVO;
import com.spring.web.vo.memberVO;

public interface AccountService {

	int getIdCnt(memberVO searchVO);

	void insertMember(memberVO searchVO);

	memberVO actionLogin(memberVO searchVO);

	void updateLastLogin(memberVO searchVO);


}