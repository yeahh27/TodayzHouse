package com.th.sess.biz;

import com.th.member.vo.MemberVO;
import com.th.sess.vo.SessVO;

public interface SessBiz {
	
	public int insertMember(SessVO sessVO);
	
	public int deleteMember(SessVO sessVO);
	
	public int selectMember(SessVO sessVO);
	
	public int updateMember(MemberVO memberVO);

}
