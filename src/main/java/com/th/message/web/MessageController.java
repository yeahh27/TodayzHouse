package com.th.message.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.th.message.service.MessageService;
import com.th.message.vo.MessageVO;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/message/send")
	public String doSendMessageAction (@ModelAttribute MessageVO messageVO, HttpServletRequest request) {
		
		if(!this.messageService.findMessage(messageVO)) {
			this.messageService.registMessage(messageVO);
		}
		
		return "redirect:" + request.getHeader("Referer");
	}
	
	@PostMapping("/message/delete/{messageId}")
	@ResponseBody
	public boolean doDeleteMessageAction(@PathVariable String messageId, HttpServletRequest request) {

		return this.messageService.deleteMessage(messageId);
	}

}
