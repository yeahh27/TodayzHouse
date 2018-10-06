package com.th.reply.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.reply.dao.ReplyDao;
import com.th.reply.vo.ReplyVO;

@Component
public class ReplyBizImpl implements ReplyBiz {

	@Autowired
	private ReplyDao replyDao;

	@Override
	public int insertOneReply(ReplyVO replyVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyVO> selectAllReplies(int boardId) {
		// TODO Auto-generated method stub
		return null;
	}
}
