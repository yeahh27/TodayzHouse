package com.th.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.article.biz.ArticleBiz;
import com.th.article.dao.ArticleDao;
import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;
import com.th.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleBiz articleBiz;

	@Override
	public boolean createArticle(ArticleVO articleVO) {
		return this.articleBiz.insertArticle(articleVO) > 0;
	}
	
	@Override
	public PageExplorer readAllArticles(ArticleSearchVO articleSearchVO) {
		return this.articleBiz.selectAllArticles(articleSearchVO);
	}

	@Override
	public ArticleVO readOneArticle(int boardId, String articleId) {
		return this.articleBiz.selectOneArticle(boardId, articleId);
	}
	
	@Override
	public boolean updateArticle(ArticleVO articleVO) {
		return this.articleBiz.updateArticle(articleVO) > 0;
	}
	
	@Override
	public ArticleVO readOneArticle(String articleId, MemberVO memberVO) {
		return null;
	}

	@Override
	public boolean deleteArticle(int boardId, String articleId) {
		return this.articleBiz.deleteOneArticle(boardId, articleId) > 0;
	}

}
