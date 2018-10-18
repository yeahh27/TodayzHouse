package com.th.message.biz;

import java.util.List;

import com.th.message.vo.MessageVO;

public interface MessageBiz {

	public int insertMessage(MessageVO messageVO);
	
	public int deleteMessage(String messageId);
	
	public List<MessageVO> selectMessageList(String toEmail);
}
