package com.th.recommend.biz;

import com.th.recommend.vo.RecommendVO;

public interface RecommendBiz {

	public int insertRecommend(RecommendVO recommendVO);
	
	public int selectRecommendByArticle(int boardId, String articleId, String email);
	
	public int deleteRecommend(RecommendVO recommendVO);
	
	public int deleteAllRecommendsByArticle(int boardId, String articleId);
	
	public int selectRecommendCountByArticle(int boardId, String articleId);
}
