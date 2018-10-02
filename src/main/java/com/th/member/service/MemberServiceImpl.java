package com.th.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.common.session.Session;
import com.th.common.util.SHA256Util;
import com.th.member.biz.MemberBiz;
import com.th.member.dao.MemberDao;
import com.th.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberBiz memberBiz;

	@Override
	public boolean registMember(MemberVO memberVO) {
		return this.memberBiz.registMember(memberVO);
	}
	
	@Override
	public boolean isDuplicateEmail(String email) {
		return this.memberBiz.isDuplicateEmail(email);
	}

	@Override
	public boolean loginMember(MemberVO memberVO, HttpSession session) {
		return this.memberBiz.loginMember(memberVO, session);
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		return this.memberBiz.loginMember(memberVO);
	}

	@Override
	public int updatePoint(String email, int point) {
		return this.memberBiz.updatePoint(email, point);
	}

	@Override
	public boolean isBlockUser(String email) {
		return this.memberBiz.isBlockUser(email);
	}

	@Override
	public boolean unblockUser(String email) {
		return this.memberBiz.unblockUser(email);
	}

	@Override
	public boolean blockUser(String email) {
		return this.memberBiz.blockUser(email);
	}

	@Override
	public boolean increaseLoginFailCount(String email) {
		return this.memberBiz.increaseLoginFailCount(email);
	}
	
	

}
