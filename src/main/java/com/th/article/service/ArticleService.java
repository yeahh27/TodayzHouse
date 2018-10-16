package com.th.article.service;

import java.util.List;

import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;
import com.th.files.vo.FilesVO;
import com.th.link.vo.LinkVO;
import com.th.member.vo.MemberVO;
import com.th.reply.vo.ReplyVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface ArticleService {

	public boolean createArticle(ArticleVO articleVO);
	
	//public List<ArticleVO> readAllArticles(int boardId);
	public PageExplorer readAllArticles(ArticleSearchVO articleSearchVO);
	
	public boolean updateArticle(ArticleVO articleVO);
	
	public ArticleVO readOneArticle(int boardId, String articleId);
	
	public boolean deleteArticle(int boardId, String articleId);
	
	public boolean isRecommend(int boardId, String articleId, String email);
	
	public boolean isReport(int boardId, String articleId, String email);
	
	public List<LinkVO> readLinkList(String fileId);
	
	public boolean updateViewCount(int boardId, String articleId);
	
	public boolean isWriterLogin(String email, String name);
	
	public List<ArticleVO> readBestArticles(int boardId);
	
}
