package com.th.link.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.link.vo.LinkVO;

@Repository
public class LinkDaoImpl extends SqlSessionDaoSupport implements LinkDao {

	private Logger logger = LoggerFactory.getLogger(LinkDaoImpl.class);
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Init MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertLink(LinkVO linkVO) {
		return this.getSqlSession().insert("LinkDao.insertLink", linkVO);
	}

	@Override
	public List<LinkVO> selectAllLinksByArticle(String fileId) {
		return this.getSqlSession().selectList("LinkDao.selectAllLinksByArticle", fileId);
	}

	@Override
	public LinkVO selectOneLink(Map<String, Object> param) {
		return this.getSqlSession().selectOne("LinkDao.selectOneLink", param);
	}
}
