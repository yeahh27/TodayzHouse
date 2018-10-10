package com.th.recommend.biz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.recommend.dao.RecommendDao;
import com.th.recommend.vo.RecommendVO;

@Component
public class RecommendBizImpl implements RecommendBiz {
	
	@Autowired
	private RecommendDao recommendDao;

	@Override
	public int insertRecommend(RecommendVO recommendVO) {
		return this.recommendDao.insertRecommend(recommendVO);
	}

	@Override
	public int selectRecommendByArticle(int boardId, String articleId, String email) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		param.put("email", email);
		
		return this.recommendDao.selectRecommendByArticle(param);
	}

	@Override
	public int deleteRecommend(RecommendVO recommendVO) {
		return this.recommendDao.deleteRecommend(recommendVO);
	}

	@Override
	public int deleteAllRecommendsByArticle(int boardId, String articleId) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		
		return this.recommendDao.deleteAllRecommendsByArticle(param);
	}

	@Override
	public int selectRecommendCountByArticle(int boardId, String articleId) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		
		return this.recommendDao.selectRecommendCountByArticle(param);
	}

}
