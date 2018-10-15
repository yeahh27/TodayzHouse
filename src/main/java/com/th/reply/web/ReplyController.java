package com.th.reply.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.th.common.session.Session;
import com.th.member.vo.MemberVO;
import com.th.reply.service.ReplyService;
import com.th.reply.vo.ReplyVO;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/reply/write") 
	public String doReplyWriteAction (@Valid @ModelAttribute ReplyVO replyVO, @SessionAttribute(Session.MEMBER) MemberVO memberVO
									  , @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		
		if(!replyVO.getToken().equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		String email = memberVO.getEmail();
		replyVO.setEmail(email);
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		replyVO.setContent(filter.doFilter(replyVO.getContent()));
		
		boolean isSuccess = this.replyService.createOneReply(replyVO);
		
		return "redirect:/board/" + replyVO.getBoardId() + "/" + replyVO.getArticleId();
	}
	
	@GetMapping("/reply/delete/{boardId}/{articleId}/{replyId}")
	@ResponseBody
	public Map<String, Object> doReplyDeleteAction (@PathVariable int boardId, @PathVariable String articleId
									   , @PathVariable String replyId, @ModelAttribute ReplyVO reply) {
		Map<String, Object> result = new HashMap<>();
		
		boolean isSuccess = this.replyService.deleteOneReply(boardId, articleId, replyId);
		if(isSuccess) {
			result.put("status", "ok");
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/reply/modify/{boardId}/{articleId}/{replyId}")
	@ResponseBody
	public Map<String, Object> doReplyModifyAction (@PathVariable int boardId, @PathVariable String articleId
									   , @PathVariable String replyId, @ModelAttribute ReplyVO replyVO
									   , HttpSession session) {
		
		String sessionToken = (String) session.getAttribute(Session.CSRF_TOKEN);
		if(!replyVO.getToken().equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		Map<String, Object> result = new HashMap<>();
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		replyVO.setContent(filter.doFilter(replyVO.getContent()));
		
		boolean isSuccess = this.replyService.modifyOneReply(replyVO);
		
		if(isSuccess) {
			result.put("status", "ok");
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}
		
}
