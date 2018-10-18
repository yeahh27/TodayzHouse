package com.th.admin.service;

import java.util.List;

import com.th.article.vo.ArticleVO;
import com.th.member.vo.MemberVO;

public interface AdminService {
	
	public List<ArticleVO> selectArticlesByAdmin();
	
	public List<MemberVO> selectMembersByAdmin();
	
	public boolean updateDeleteYN(int boardId, String articleId);

}
