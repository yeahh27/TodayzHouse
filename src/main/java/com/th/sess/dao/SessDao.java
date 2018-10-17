package com.th.sess.dao;

import com.th.member.vo.MemberVO;
import com.th.sess.vo.SessVO;

public interface SessDao {
	
	public int insertMember(SessVO sessVO);
	
	public int deleteMember(SessVO sessVO);
	
	public int selectMember(SessVO sessVO);
	
	public int updateMember(MemberVO memberVO);

}
