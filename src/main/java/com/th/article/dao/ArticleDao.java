package com.th.article.dao;

import java.util.List;
import java.util.Map;

import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface ArticleDao {
	
	public int insertArticle(ArticleVO articleVO);
	
	//public List<ArticleVO> selectAllArticles(int boardId);
	public List<ArticleVO> selectAllArticles(ArticleSearchVO articleSearchVO);
	public int selectAllArticlesCount(ArticleSearchVO articleSearchVO);
	
	public ArticleVO selectOneArticle(Map<String, Object> param);

	public int updateArticle(ArticleVO articleVO);
	
	public int deleteOneArticle(Map<String, Object> param);

}
