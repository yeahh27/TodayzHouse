package com.th.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.article.biz.ArticleBiz;
import com.th.article.vo.ArticleVO;
import com.th.member.biz.MemberBiz;
import com.th.member.vo.MemberVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private ArticleBiz articleBiz;
	
	@Autowired
	private MemberBiz memberBiz;

	@Override
	public List<ArticleVO> selectArticlesByAdmin() {
		return this.articleBiz.selectArticlesByAdmin();
	}

	@Override
	public List<MemberVO> selectMembersByAdmin() {
		return this.memberBiz.selectMembersByAdmin();
	}

	@Override
	public boolean updateDeleteYN(int boardId, String articleId) {
		boolean isSuccess = this.articleBiz.updateDeleteYN(boardId, articleId) > 0;
		
		if(isSuccess) {
			ArticleVO articleVO = this.articleBiz.selectOneArticle(boardId, articleId);
			this.memberBiz.updateDeleteArtCount(articleVO.getEmail());
		}
		
		return isSuccess;
	}

}
