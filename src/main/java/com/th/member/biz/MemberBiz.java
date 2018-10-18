package com.th.member.biz;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.th.member.vo.MemberVO;

public interface MemberBiz {

	public boolean registMember(MemberVO memberVO);
	
	public boolean isDuplicateEmail(String email);
	
	//public MemberVO loginMember(MemberVO memberVO, HttpSession session);
	public boolean loginMember(MemberVO memberVO, HttpSession session);
	public MemberVO loginMember(MemberVO memberVO);
	
	public int updatePoint(String email, int point);
	
	public boolean isBlockUser(String email);
	public boolean unblockUser(String email);
	public boolean blockUser(String email);
	public boolean increaseLoginFailCount(String email);
	
	public int updateMember(MemberVO memberVO);
	
	public int updateChatOk(String email, int chatOk);
	
	public List<MemberVO> selectMembersByAdmin();
	
	public int deleteMemberByAdmin(String email);
	
	public int updateDeleteArtCount(String email);
}
