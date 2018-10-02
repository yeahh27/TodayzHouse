package com.th.article.service;

import java.util.List;

import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;
import com.th.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface ArticleService {

	public boolean createArticle(ArticleVO articleVO);
	
	//public List<ArticleVO> readAllArticles(int boardId);
	public PageExplorer readAllArticles(ArticleSearchVO articleSearchVO);
	
	public boolean updateArticle(ArticleVO articleVO);
	
	public ArticleVO readOneArticle(String articleId, MemberVO memberVO);
	
	public ArticleVO readOneArticle(int boardId, String articleId);
	
	public boolean deleteArticle(int boardId, String articleId);
}
