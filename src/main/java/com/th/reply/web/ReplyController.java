package com.th.reply.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String doReplyWriteAction (@Valid @ModelAttribute ReplyVO replyVO, @SessionAttribute(Session.MEMBER) MemberVO memberVO) {
		
		String email = memberVO.getEmail();
		replyVO.setEmail(email);
		
		boolean isSuccess = this.replyService.createOneReply(replyVO);
		
		return "redirect:/board/" + replyVO.getBoardId() + "/" + replyVO.getArticleId() ;
	}
		
}
