package com.th.member.dao;

import java.util.List;
import java.util.Map;

import com.th.member.vo.MemberVO;

public interface MemberDao {

	public int insertMember(MemberVO memberVO);
	
	public int isDuplicateEmail(String email);
	
	public MemberVO selectOneMember(MemberVO memberVO);
	
	public int updatePoint(Map<String, Object> param);
	
	public String getSaltById(String email);

	public int isBlockUser(String email);
	public boolean unblockUser(String email);
	public boolean blockUser(String email);
	public boolean increaseLoginFailCount(String email);
	
	public int updateMember(MemberVO memberVO);
	
	public int updateChatOk(Map<String, Object> param);
	
	public List<MemberVO> selectMembersByAdmin();
	
	public int deleteMemberByAdmin(String email);
	
	public int updateDeleteArtCount(String email);
}
