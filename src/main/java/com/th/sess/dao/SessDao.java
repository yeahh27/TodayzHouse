package com.th.sess.dao;

import com.th.sess.vo.SessVO;

public interface SessDao {
	
	public int insertMember(SessVO sessVO);
	
	public int deleteMember(SessVO sessVO);
	
	public int selectMember(SessVO sessVO);

}
