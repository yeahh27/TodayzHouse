package com.th.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.article.biz.ArticleBiz;
import com.th.recommend.biz.RecommendBiz;
import com.th.recommend.vo.RecommendVO;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Autowired
	private RecommendBiz recommendBiz;
	
	@Autowired
	private ArticleBiz articleBiz;

	@Override
	public boolean createRecommend(RecommendVO recommendVO) {
		boolean isSuccess = this.recommendBiz.insertRecommend(recommendVO) > 0;
		if(isSuccess) {
			this.articleBiz.updateRecommend(recommendVO.getBoardId(), recommendVO.getArticleId(), this.recommendBiz.selectRecommendCountByArticle(recommendVO.getBoardId(), recommendVO.getArticleId()));
		}
		return isSuccess;
	}

	@Override
	public boolean deleteRecommend(RecommendVO recommendVO) {
		boolean isSuccess = this.recommendBiz.deleteRecommend(recommendVO) > 0;
		if(isSuccess) {
			this.articleBiz.updateRecommend(recommendVO.getBoardId(), recommendVO.getArticleId(), this.recommendBiz.selectRecommendCountByArticle(recommendVO.getBoardId(), recommendVO.getArticleId()));
		}
		return isSuccess;
	}

	@Override
	public int readRecommendCount(int boardId, String articleId) {
		return this.recommendBiz.selectRecommendCountByArticle(boardId, articleId);
	}

}
