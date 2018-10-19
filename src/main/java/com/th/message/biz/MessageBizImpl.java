package com.th.message.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.message.dao.MessageDao;
import com.th.message.vo.MessageVO;

@Component
public class MessageBizImpl implements MessageBiz {
	
	@Autowired
	private MessageDao messageDao;

	@Override
	public int insertMessage(MessageVO messageVO) {
		return this.messageDao.insertMessage(messageVO);
	}

	@Override
	public int deleteMessage(String messageId) {
		return this.messageDao.deleteMessage(messageId);
	}

	@Override
	public List<MessageVO> selectMessageList(String toEmail) {
		return this.messageDao.selectMessageList(toEmail);
	}

	@Override
	public int selectMessage(MessageVO messageVO) {
		return this.messageDao.selectMessage(messageVO);
	}
	
	

}
