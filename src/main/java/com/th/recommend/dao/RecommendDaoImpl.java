package com.th.recommend.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.recommend.vo.RecommendVO;

@Repository
public class RecommendDaoImpl extends SqlSessionDaoSupport implements RecommendDao {
	
	private Logger logger = LoggerFactory.getLogger(RecommendDaoImpl.class);

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Init MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertRecommend(RecommendVO recommendVO) {
		return this.getSqlSession().insert("RecommendDao.insertRecommend", recommendVO);
	}

	@Override
	public int selectRecommendByArticle(Map<String, Object> param) {
		return this.getSqlSession().selectOne("RecommendDao.selectRecommendByArticle", param);
	}

	@Override
	public int deleteRecommend(RecommendVO recommendVO) {
		return this.getSqlSession().delete("RecommendDao.deleteRecommend", recommendVO);
	}

	@Override
	public int deleteAllRecommendsByArticle(Map<String, Object> param) {
		return this.getSqlSession().delete("RecommendDao.deleteAllRecommendsByArticle", param);
	}

	@Override
	public int selectRecommendCountByArticle(Map<String, Object> param) {
		return this.getSqlSession().selectOne("RecommendDao.selectRecommendCountByArticle", param);
	}

}
