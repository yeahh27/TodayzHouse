package com.th.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.reply.biz.ReplyBiz;
import com.th.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyBiz replyBiz;

	@Override
	public boolean createOneReply(ReplyVO replyVO) {
		// TODO Auto-generated method stub
		return false;
	}
}
