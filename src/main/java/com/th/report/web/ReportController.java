package com.th.report.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.th.common.session.Session;
import com.th.member.vo.MemberVO;
import com.th.recommend.vo.RecommendVO;
import com.th.report.service.ReportService;
import com.th.report.vo.ReportVO;

@Controller
public class ReportController {
	
	@Autowired 
	private ReportService reportService;
	
	@PostMapping("/report/{boardId}/{articleId}")
	@ResponseBody
	public Map<String, Object> doReportAction (@PathVariable int boardId, @PathVariable String articleId
											   , @RequestBody String token, HttpSession session, @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		token = token.substring(6);
		if(!token.equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		Map<String, Object> result = new HashMap<>();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ReportVO reportVO = new ReportVO();
		reportVO.setArticleId(articleId);
		reportVO.setBoardId(boardId);
		reportVO.setEmail(memberVO.getEmail());
		
		boolean isSuccess = this.reportService.createReport(reportVO);
		
		if(isSuccess) {
			result.put("status", "ok");
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/unreport/{boardId}/{articleId}")
	@ResponseBody
	public Map<String, Object> doUnReportAction (@PathVariable int boardId, @PathVariable String articleId
												 , @RequestBody String token, HttpSession session, @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		token = token.substring(6);
		if(!token.equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		Map<String, Object> result = new HashMap<>();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ReportVO reportVO = new ReportVO();
		reportVO.setArticleId(articleId);
		reportVO.setBoardId(boardId);
		reportVO.setEmail(memberVO.getEmail());
		
		boolean isSuccess = this.reportService.deleteReport(reportVO);
		
		if(isSuccess) {
			result.put("status", "ok");
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/report/count/{boardId}/{articleId}")
	@ResponseBody
	public Map<String, Object> doRecommendCountAction (@PathVariable int boardId, @PathVariable String articleId, HttpSession session) {
		Map<String, Object> result = new HashMap<>();

		int recommendCount = this.reportService.readReportCount(boardId, articleId);
		result.put("recommendCount", recommendCount);
		
		return result;
	}

}
