package com.th.reply.biz;

import java.util.List;

import com.th.reply.vo.ReplyVO;

public interface ReplyBiz {

	public int insertOneReply(ReplyVO replyVO);
	
	public List<ReplyVO> selectAllReplies(int boardId, String articleId);
}
