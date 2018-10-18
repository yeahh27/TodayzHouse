package com.th.reply.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.article.dao.ArticleDaoImpl;
import com.th.reply.vo.ReplyVO;

@Repository
public class ReplyDaoImpl extends SqlSessionDaoSupport implements ReplyDao {

	private Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertOneReply(ReplyVO replyVO) {
		return this.getSqlSession().insert("ReplyDao.insertOneReply", replyVO);
	}

	@Override
	public List<ReplyVO> selectAllReplies(Map<String, Object> param) {
		return this.getSqlSession().selectList("ReplyDao.selectAllReplies", param);
	}

	@Override
	public int deleteAllReplies(Map<String, Object> param) {
		return this.getSqlSession().delete("ReplyDao.deleteAllReplies", param);
	}

	@Override
	public int deleteOneReply(Map<String, Object> param) {
		return this.getSqlSession().delete("ReplyDao.deleteOneReply", param);
	}
	
	@Override
	public int updateDeleteYn(Map<String, Object> param) {
		return this.getSqlSession().update("ReplyDao.updateDeleteYn", param);
	}

	@Override
	public int updateOneReply(ReplyVO replyVO) {
		return this.getSqlSession().update("ReplyDao.updateOneReply", replyVO);
	}
}
