package com.th.reply.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.th.reply.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
}
