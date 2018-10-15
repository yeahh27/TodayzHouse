package com.th.sess.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SessDaoImpl extends SqlSessionDaoSupport implements SessDao {

	private Logger logger = LoggerFactory.getLogger(SessDaoImpl.class);
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
}
