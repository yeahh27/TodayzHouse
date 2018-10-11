package com.th.article.biz;

import java.util.List;
import java.util.Map;

import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface ArticleBiz {

	public Map<String, Object> insertArticle(ArticleVO articleVO);
	
	//public List<ArticleVO> selectAllArticles(int boardId);
	public PageExplorer selectAllArticles(ArticleSearchVO articleSearchVO);
	
	public ArticleVO selectOneArticle(int boardId, String articleId);

	public int updateArticle(ArticleVO articleVO);
	
	public int deleteOneArticle(int boardId, String articleId);
	
	public int updateViewCount(int boardId, String articleId);
	
	public int updateRecommend(int boardId, String articleId, int upDown);
	
	public int updateReport(int boardId, String articleId, int upDown);
	
}
