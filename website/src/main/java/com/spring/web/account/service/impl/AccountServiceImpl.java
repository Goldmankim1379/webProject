package com.spring.web.account.service.impl;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.spring.web.account.service.AccountService;
import com.spring.web.vo.LoginVO;
import com.spring.web.vo.memberVO;

@Service("AccountService")
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountMapper mapper;

	@Override
	public int getIdCnt(memberVO searchVO) {
		return mapper.getIdCnt(searchVO);
	}

	@Override
	public void insertMember(memberVO searchVO) {
		mapper.insertMember(searchVO);
	}

	@Override
	public memberVO actionLogin(memberVO searchVO) {
		return mapper.actionLogin(searchVO);
	}

	@Override
	public void updateLastLogin(memberVO searchVO) {
		mapper.updateLastLogin(searchVO);
		
	}

	@Override
	public memberVO memberIdSearch(memberVO searchVO) {
		return mapper.memberIdSearch(searchVO);
	}

	@Override
	public int memberPwdCheck(memberVO searchVO) {
		return mapper.memberPwdCheck(searchVO);
	}

	@Override
	public void passwordUpdate(memberVO searchVO) {
		mapper.passwordUpdate(searchVO);
	}

	@Override
	public void memberUpdate(memberVO searchVO) {
		if(!StringUtils.isNullOrEmpty(searchVO.getMe_pwd())) {
			try {
				String enpassword = encryptPassword(searchVO.getMe_pwd());
				searchVO.setMe_pwd(enpassword);
				mapper.memberPasswordUpdate(searchVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mapper.memberUpdate(searchVO);
		}
	}
	
	
	
	
	
	public static String encryptPassword(String data) throws Exception {

		if (data == null) {
			return "";
		}

		byte[] plainText = null; // 평문
		byte[] hashValue = null; // 해쉬값
		plainText = data.getBytes();

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		hashValue = md.digest(plainText);

		return new String(Base64.encodeBase64(hashValue));
	}

	@Override
	public memberVO memberInfoSearch(memberVO searchVO) {
		return mapper.memberInfoSearch(searchVO);
	}



}
