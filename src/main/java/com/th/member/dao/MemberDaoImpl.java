package com.th.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.member.vo.MemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	private Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertMember(MemberVO memberVO) {
		return getSqlSession().insert("MemberDao.insertMember", memberVO);
	}

	@Override
	public int isDuplicateEmail(String email) {
		return getSqlSession().selectOne("MemberDao.isDuplicateEmail", email);
	}
	
	@Override
	public MemberVO selectOneMember(MemberVO memberVO) {
		return getSqlSession().selectOne("MemberDao.selectOneMember", memberVO);
	}

	@Override
	public int updatePoint(Map<String, Object> param) {
		return getSqlSession().update("MemberDao.updatePoint", param);
	}

	@Override
	public String getSaltById(String email) {
		return getSqlSession().selectOne("MemberDao.getSaltById", email);
	}

	@Override
	public int isBlockUser(String email) {
		return getSqlSession().selectOne("MemberDao.isBlockUser", email);
	}

	@Override
	public boolean unblockUser(String email) {
		return getSqlSession().update("MemberDao.unblockUser", email) > 0;
	}

	@Override
	public boolean blockUser(String email) {
		return getSqlSession().update("MemberDao.blockUser", email) > 0;
	}

	@Override
	public boolean increaseLoginFailCount(String email) {
		return getSqlSession().update("MemberDao.increaseLoginFailCount", email) > 0;
	}

}
