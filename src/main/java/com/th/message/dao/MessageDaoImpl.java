package com.th.message.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.message.vo.MessageVO;

@Repository
public class MessageDaoImpl extends SqlSessionDaoSupport implements MessageDao {
	
	private Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertMessage(MessageVO messageVO) {
		return this.getSqlSession().insert("MessageDao.insertMessage", messageVO);
	}

	@Override
	public int deleteMessage(String messageId) {
		return this.getSqlSession().delete("MessageDao.deleteMessage", messageId);
	}

	@Override
	public List<MessageVO> selectMessageList(String toEmail) {
		return this.getSqlSession().selectList("MessageDao.selectMessageList", toEmail);
	}

}
