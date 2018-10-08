package com.th.reply.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
		
		boolean isSuccess = this.replyService.createOneReply(replyVO);
		
		return "redirect:/board/" + replyVO.getBoardId() + "/" + replyVO.getArticleId();
	}
	
	@GetMapping("/reply/delete/{boardId}/{articleId}/{replyId}")
	public String doReplyDeleteAction (@PathVariable int boardId, @PathVariable String articleId
									   , @PathVariable String replyId, @ModelAttribute ReplyVO reply) {
		boolean isSuccess = this.replyService.deleteOneReply(boardId, articleId, replyId);
		return "redirect:/board/" + boardId + "/" + articleId;
	}
		
}
