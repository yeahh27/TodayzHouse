package com.th.reply.dao;

import java.util.List;

import com.th.reply.vo.ReplyVO;

public interface ReplyDao {

	public int insertOneReply(ReplyVO replyVO);
	
	public List<ReplyVO> selectAllReplies(int boardId);
}
