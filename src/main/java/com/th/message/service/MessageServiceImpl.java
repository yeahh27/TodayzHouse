package com.th.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.message.biz.MessageBiz;
import com.th.message.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageBiz messageBiz;

	@Override
	public boolean registMessage(MessageVO messageVO) {
		return this.messageBiz.insertMessage(messageVO) > 0;
	}

	@Override
	public boolean deleteMessage(String messageId) {
		return this.messageBiz.deleteMessage(messageId) > 0;
	}

	@Override
	public boolean findMessage(MessageVO messageVO) {
		return this.messageBiz.selectMessage(messageVO) > 0;
	}
	
	

}
