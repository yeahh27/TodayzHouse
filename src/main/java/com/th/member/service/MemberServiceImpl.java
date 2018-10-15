package com.th.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.common.session.Session;
import com.th.common.util.SHA256Util;
import com.th.member.biz.MemberBiz;
import com.th.member.dao.MemberDao;
import com.th.member.vo.MemberVO;
import com.th.sess.biz.SessBiz;
import com.th.sess.vo.SessVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberBiz memberBiz;
	
	@Autowired
	private SessBiz sessBiz;

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
		boolean isLogin = this.memberBiz.loginMember(memberVO, session);
		
		if(isLogin) {
			MemberVO loginMemberVO = (MemberVO) session.getAttribute(Session.MEMBER);
			this.sessBiz.insertMember(new SessVO(loginMemberVO.getEmail(), loginMemberVO.getName()));
		}
		return isLogin;
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		this.sessBiz.insertMember(new SessVO(memberVO.getEmail(), memberVO.getName()));
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

	@Override
	public boolean logoutMember(MemberVO memberVO) {
		return this.sessBiz.deleteMember(new SessVO(memberVO.getEmail(), memberVO.getName())) > 0;
	}

}
