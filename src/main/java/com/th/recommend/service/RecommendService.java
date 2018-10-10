package com.th.recommend.service;

import com.th.recommend.vo.RecommendVO;

public interface RecommendService {
	
	public boolean createRecommend(RecommendVO recommendVO);
	
	public boolean deleteRecommend(RecommendVO recommendVO);
	
	public int readRecommendCount(int boardId, String articleId);
}
