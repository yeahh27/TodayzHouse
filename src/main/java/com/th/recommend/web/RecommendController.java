package com.th.recommend.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.th.common.session.Session;
import com.th.member.vo.MemberVO;
import com.th.recommend.service.RecommendService;
import com.th.recommend.vo.RecommendVO;

@Controller
public class RecommendController {

	@Autowired
	private RecommendService recommendService;
	
	@PostMapping("/recommend/{boardId}/{articleId}")
	@ResponseBody
	public Map<String, Object> doRecommendAction (@PathVariable int boardId, @PathVariable String articleId
												  , @RequestParam String token, HttpSession session, @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		if(!token.equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		Map<String, Object> result = new HashMap<>();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		RecommendVO recommendVO = new RecommendVO();
		recommendVO.setBoardId(boardId);
		recommendVO.setArticleId(articleId);
		recommendVO.setEmail(memberVO.getEmail());
		
		boolean isSuccess = this.recommendService.createRecommend(recommendVO);
		
		if(isSuccess) {
			result.put("status", "ok");
			int recommendCount = this.recommendService.readRecommendCount(boardId, articleId);
			result.put("recommendCount", recommendCount);
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/unrecommend/{boardId}/{articleId}")
	@ResponseBody
	public Map<String, Object> doUnRecommendAction (@PathVariable int boardId, @PathVariable String articleId
												    , @RequestParam String token, HttpSession session, @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		if(!token.equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		Map<String, Object> result = new HashMap<>();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		RecommendVO recommendVO = new RecommendVO();
		recommendVO.setBoardId(boardId);
		recommendVO.setArticleId(articleId);
		recommendVO.setEmail(memberVO.getEmail());
		
		boolean isSuccess = this.recommendService.deleteRecommend(recommendVO);
		
		if(isSuccess) {
			result.put("status", "ok");
			int recommendCount = this.recommendService.readRecommendCount(boardId, articleId);
			result.put("recommendCount", recommendCount);
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}

}
