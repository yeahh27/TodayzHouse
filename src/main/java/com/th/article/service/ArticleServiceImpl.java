package com.th.article.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.article.biz.ArticleBiz;
import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;
import com.th.files.biz.FilesBiz;
import com.th.recommend.biz.RecommendBiz;
import com.th.reply.biz.ReplyBiz;
import com.th.report.biz.ReportBiz;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleBiz articleBiz;
	
	@Autowired
	private FilesBiz filesBiz;

	@Autowired
	private ReplyBiz replyBiz;
	
	@Autowired
	private RecommendBiz recommendBiz;
	
	@Autowired
	private ReportBiz reportBiz;
	
	@Override
	public boolean createArticle(ArticleVO articleVO) {
		Map<String, Object> result = this.articleBiz.insertArticle(articleVO);
		int insertArticleResult = (int) result.get("insertArticleResult");
		if(articleVO.getFileVOList() != null) {
			for(int i=0; i<articleVO.getFileVOList().size(); i++) {
				articleVO.getFileVOList().get(i).setArticleId(result.get("articleId").toString());
				this.filesBiz.insertFile(articleVO.getFileVOList().get(i));
			}
		}
		return insertArticleResult > 0;
	}
	
	@Override
	public PageExplorer readAllArticles(ArticleSearchVO articleSearchVO) {
		return this.articleBiz.selectAllArticles(articleSearchVO);
	}

	@Override
	public ArticleVO readOneArticle(int boardId, String articleId) {
		this.articleBiz.updateViewCount(boardId, articleId);
		ArticleVO articleVO = this.articleBiz.selectOneArticle(boardId, articleId);
		articleVO.setReplyList(this.replyBiz.selectAllReplies(boardId, articleId));
		articleVO.setRecommend(this.recommendBiz.selectRecommendCountByArticle(boardId, articleId));
		return articleVO;
	}
	
	@Override
	public boolean updateArticle(ArticleVO articleVO) {
		if(articleVO.getFileVOList() != null) {
			for(int i=0; i<articleVO.getFileVOList().size(); i++) {
				this.filesBiz.insertFile(articleVO.getFileVOList().get(i));
			}
		}
		return this.articleBiz.updateArticle(articleVO) > 0;
	}

	@Override
	public boolean deleteArticle(int boardId, String articleId) {
		if(this.filesBiz.selectAllFiles(boardId, articleId) != null) {
			this.filesBiz.deleteFilesByArticle(boardId, articleId);
		}
		
		if(this.replyBiz.selectAllReplies(boardId, articleId) != null) {
			this.replyBiz.deleteAllReplies(boardId, articleId);
		}
		
		this.recommendBiz.deleteAllRecommendsByArticle(boardId, articleId);
		this.reportBiz.deleteAllReportsByArticle(boardId, articleId);
		
		return this.articleBiz.deleteOneArticle(boardId, articleId) > 0;
	}

	@Override
	public boolean isRecommend(int boardId, String articleId, String email) {
		return this.recommendBiz.selectRecommendByArticle(boardId, articleId, email) > 0;
	}

	@Override
	public boolean isReport(int boardId, String articleId, String email) {
		return this.reportBiz.selectReportByArticle(boardId, articleId, email) > 0;
	}

}
