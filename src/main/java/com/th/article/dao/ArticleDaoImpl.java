package com.th.article.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;

@Repository
public class ArticleDaoImpl extends SqlSessionDaoSupport implements ArticleDao {

	private Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public Map<String, Object> insertArticle(ArticleVO articleVO) {
		Map<String, Object> result = new HashMap<>();
		int insertArticleResult = getSqlSession().insert("ArticleDao.insertArticle", articleVO);
		
		result.put("insertArticleResult", insertArticleResult);
		result.put("articleId", articleVO.getArticleId());
		
		return result;
	}
	
	@Override
	public List<ArticleVO> selectAllArticles(ArticleSearchVO articleSearchVO) {
		return getSqlSession().selectList("ArticleDao.selectAllArticles", articleSearchVO);
	}
	
	@Override
	public int selectAllArticlesCount(ArticleSearchVO articleSearchVO) {
		return getSqlSession().selectOne("ArticleDao.selectAllArticlesCount", articleSearchVO);
	}
	
	@Override
	public ArticleVO selectOneArticle(Map<String, Object> param) {
		return getSqlSession().selectOne("ArticleDao.selectOneArticle", param);
	}
	
	@Override
	public int updateArticle(ArticleVO articleVO) {
		return getSqlSession().update("ArticleDao.updateArticle", articleVO);
	}

	@Override
	public int deleteOneArticle(Map<String, Object> param) {
		return getSqlSession().delete("ArticleDao.deleteOneArticle", param);
	}
	
	@Override
	public int updateDeleteYN(Map<String, Object> param) {
		return this.getSqlSession().update("ArticleDao.updateDeleteYN", param);
	}

	@Override
	public int updateViewCount(Map<String, Object> param) {
		return this.getSqlSession().update("ArticleDao.updateViewCount", param);
	}

	@Override
	public int updateRecommed(Map<String, Object> param) {
		return this.getSqlSession().update("ArticleDao.updateRecommed", param);
	}

	@Override
	public int updateReport(Map<String, Object> param) {
		return this.getSqlSession().update("ArticleDao.updateReport", param);
	}

	@Override
	public List<ArticleVO> selectBestArticles(int boardId) {
		return this.getSqlSession().selectList("ArticleDao.selectBestArticles", boardId);
	}

	@Override
	public List<ArticleVO> selectAllArticlesByEmail(String email) {
		return this.getSqlSession().selectList("ArticleDao.selectAllArticlesByEmail", email);
	}

	@Override
	public List<ArticleVO> selectArticlesByAdmin() {
		return this.getSqlSession().selectList("ArticleDao.selectArticlesByAdmin");
	}

	@Override
	public int deleteArticleByAdmin(Map<String, Object> param) {
		return this.getSqlSession().delete("", param);
	}

}
