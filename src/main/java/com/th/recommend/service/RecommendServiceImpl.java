package com.th.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.recommend.biz.RecommendBiz;
import com.th.recommend.vo.RecommendVO;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Autowired
	private RecommendBiz recommendBiz;

	@Override
	public boolean createRecommend(RecommendVO recommendVO) {
		return this.recommendBiz.insertRecommend(recommendVO) > 0;
	}

	@Override
	public boolean deleteRecommend(RecommendVO recommendVO) {
		return this.recommendBiz.deleteRecommend(recommendVO) > 0;
	}

	@Override
	public int readRecommendCount(int boardId, String articleId) {
		return this.recommendBiz.selectRecommendCountByArticle(boardId, articleId);
	}

}
