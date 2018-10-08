package com.th.reply.dao;

import java.util.List;
import java.util.Map;

import com.th.reply.vo.ReplyVO;

public interface ReplyDao {

	public int insertOneReply(ReplyVO replyVO);
	
	public List<ReplyVO> selectAllReplies(Map<String, Object> param);
}
