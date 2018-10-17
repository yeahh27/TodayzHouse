package com.th.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.th.member.vo.MemberVO;

public interface MemberService {

	public boolean registMember(MemberVO memberVO);
	
	public boolean isDuplicateEmail(String email);
	
	//public MemberVO loginMember(MemberVO memberVO, HttpSession session);
	public boolean loginMember(MemberVO memberVO, HttpSession session);
	public MemberVO loginMember(MemberVO memberVO);
	
	public boolean logoutMember(MemberVO memberVO);
	
	public int updatePoint(String email, int point);
	
	public boolean isBlockUser(String email);
	public boolean unblockUser(String email);
	public boolean blockUser(String email);
	public boolean increaseLoginFailCount(String email);
	
	public Map<String, List> readArticles(String email);
	
	public boolean updateMember(MemberVO memberVO);
	
	public boolean updateChatOk(String email, int chatOk);
}
