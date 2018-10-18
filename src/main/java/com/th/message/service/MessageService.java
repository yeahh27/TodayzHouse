package com.th.message.service;

import com.th.message.vo.MessageVO;

public interface MessageService {
	
	public boolean registMessage(MessageVO messageVO);
	
	public boolean deleteMessage(String messageId);
	
}
