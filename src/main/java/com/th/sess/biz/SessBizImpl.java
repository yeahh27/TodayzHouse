package com.th.sess.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.member.vo.MemberVO;
import com.th.sess.dao.SessDao;
import com.th.sess.vo.SessVO;

@Component
public class SessBizImpl implements SessBiz {

	@Autowired
	private SessDao sessDao;

	@Override
	public int insertMember(SessVO sessVO) {
		return this.sessDao.insertMember(sessVO);
	}

	@Override
	public int deleteMember(SessVO sessVO) {
		return this.sessDao.deleteMember(sessVO);
	}

	@Override
	public int selectMember(SessVO sessVO) {
		return this.sessDao.selectMember(sessVO);
	}

	@Override
	public int updateMember(MemberVO memberVO) {
		return this.sessDao.updateMember(memberVO);
	}
	
}
