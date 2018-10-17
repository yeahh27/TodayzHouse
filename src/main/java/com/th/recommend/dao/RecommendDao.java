package com.th.recommend.dao;

import java.util.List;
import java.util.Map;

import com.th.recommend.vo.RecommendVO;

public interface RecommendDao {

	public int insertRecommend(RecommendVO recommendVO);
	
	public int selectRecommendByArticle(Map<String, Object> param);

	public int deleteRecommend(RecommendVO recommendVO);
	
	public int deleteAllRecommendsByArticle(Map<String, Object> param);
	
	public int selectRecommendCountByArticle(Map<String, Object> param);
	
	public List<RecommendVO> selectRecommendByEmail(String email);
}
