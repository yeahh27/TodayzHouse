package com.th.message.dao;

import java.util.List;

import com.th.message.vo.MessageVO;

public interface MessageDao {

	public int insertMessage(MessageVO messageVO);
	
	public int deleteMessage(String messageId);
	
	public List<MessageVO> selectMessageList(String toEmail);
	
	public int selectMessage(MessageVO messageVO);
}
