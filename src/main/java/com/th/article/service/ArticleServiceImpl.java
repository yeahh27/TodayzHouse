package com.th.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.article.biz.ArticleBiz;
import com.th.article.dao.ArticleDao;
import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;
import com.th.files.biz.FilesBiz;
import com.th.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleBiz articleBiz;
	
	@Autowired
	private FilesBiz filesBiz;

	@Override
	public boolean createArticle(ArticleVO articleVO) {
		Map<String, Object> result = this.articleBiz.insertArticle(articleVO);
		int insertArticleResult = (int) result.get("insertArticleResult");
		if(articleVO.getFileVO().getFile() != null) {
			articleVO.getFileVO().setArticleId(result.get("articleId").toString());
			this.filesBiz.insertFile(articleVO.getFileVO());
		}
		return insertArticleResult > 0;
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
		System.out.println("aaaaaaaaaaaaaa" +this.filesBiz.selectAllFiles(boardId, articleId)  );
		if(this.filesBiz.selectAllFiles(boardId, articleId) != null) {
			this.filesBiz.deleteFilesByArticle(boardId, articleId);
		}
		return this.articleBiz.deleteOneArticle(boardId, articleId) > 0;
	}

}
