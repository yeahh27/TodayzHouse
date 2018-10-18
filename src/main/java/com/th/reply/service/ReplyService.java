package com.th.reply.service;

import com.th.reply.vo.ReplyVO;

public interface ReplyService {

	public boolean createOneReply(ReplyVO replyVO);
	
	public boolean deleteOneReply(int boardId, String articleId, String replyId);
	public boolean updateDeleteYn(int boardId, String articleId, String replyId);
	
	public boolean modifyOneReply(ReplyVO replyVO);
}
