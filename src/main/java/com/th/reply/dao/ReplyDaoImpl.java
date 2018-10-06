package com.th.reply.dao;

import java.util.List;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyVO> selectAllReplies(int boardId) {
		// TODO Auto-generated method stub
		return null;
	}
}
