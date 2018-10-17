package com.th.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.article.biz.ArticleBiz;
import com.th.article.vo.ArticleVO;
import com.th.common.session.Session;
import com.th.member.biz.MemberBiz;
import com.th.member.vo.MemberVO;
import com.th.recommend.biz.RecommendBiz;
import com.th.recommend.vo.RecommendVO;
import com.th.report.biz.ReportBiz;
import com.th.report.vo.ReportVO;
import com.th.sess.biz.SessBiz;
import com.th.sess.vo.SessVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberBiz memberBiz;
	
	@Autowired
	private SessBiz sessBiz;
	
	@Autowired
	private ArticleBiz articleBiz;
	
	@Autowired 
	private RecommendBiz recommendBiz;
	
	@Autowired
	private ReportBiz reportBiz;

	@Override
	public boolean registMember(MemberVO memberVO) {
		return this.memberBiz.registMember(memberVO);
	}
	
	@Override
	public boolean isDuplicateEmail(String email) {
		return this.memberBiz.isDuplicateEmail(email);
	}

	@Override
	public boolean loginMember(MemberVO memberVO, HttpSession session) {
		boolean isLogin = this.memberBiz.loginMember(memberVO, session);
		
		if(isLogin) {
			MemberVO loginMemberVO = (MemberVO) session.getAttribute(Session.MEMBER);
			this.sessBiz.insertMember(new SessVO(loginMemberVO.getEmail(), loginMemberVO.getName()));
		}
		return isLogin;
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		this.sessBiz.insertMember(new SessVO(memberVO.getEmail(), memberVO.getName()));
		return this.memberBiz.loginMember(memberVO);
	}

	@Override
	public int updatePoint(String email, int point) {
		return this.memberBiz.updatePoint(email, point);
	}

	@Override
	public boolean isBlockUser(String email) {
		return this.memberBiz.isBlockUser(email);
	}

	@Override
	public boolean unblockUser(String email) {
		return this.memberBiz.unblockUser(email);
	}

	@Override
	public boolean blockUser(String email) {
		return this.memberBiz.blockUser(email);
	}

	@Override
	public boolean increaseLoginFailCount(String email) {
		return this.memberBiz.increaseLoginFailCount(email);
	}

	@Override
	public boolean logoutMember(MemberVO memberVO) {
		return this.sessBiz.deleteMember(new SessVO(memberVO.getEmail(), memberVO.getName())) > 0;
	}

	@Override
	public Map<String, List> readArticles(String email) {
		Map<String, List> result = new HashMap<>();
		
		List<RecommendVO> recommendList = this.recommendBiz.selectRecommendByEmail(email);
		List<ArticleVO> articleList = new ArrayList<>();
		for(int i=0; i<recommendList.size(); i++) {
			ArticleVO articleVO = this.articleBiz.selectOneArticle(recommendList.get(i).getBoardId(), recommendList.get(i).getArticleId());
			if(articleVO != null) {
				articleList.add(articleVO);
			}
		}
		result.put("recommends", articleList);
		
		articleList = new ArrayList<>();
		List<ReportVO> reportList = this.reportBiz.selectReportByEmail(email);
		for(int i=0; i<reportList.size(); i++) {
			ArticleVO articleVO = this.articleBiz.selectOneArticle(reportList.get(i).getBoardId(), reportList.get(i).getArticleId());
			if(articleVO != null) {
				articleList.add(articleVO);
			}
		}
		result.put("reports", articleList);
		
		result.put("my", this.articleBiz.selectAllArticlesByEmail(email));
		
		return result;
	}

	@Override
	public boolean updateMember(MemberVO memberVO) {
		boolean isSuccess = this.memberBiz.updateMember(memberVO) > 0;
		
		if(isSuccess) {
			this.sessBiz.updateMember(memberVO);
		}
		
		return isSuccess;
	}

	@Override
	public boolean updateChatOk(String email, int chatOk) {
		return this.memberBiz.updateChatOk(email, chatOk) > 0;
	}

}
