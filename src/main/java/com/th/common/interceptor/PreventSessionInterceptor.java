package com.th.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.th.common.session.Session;
import com.th.member.dao.MemberDao;
import com.th.member.vo.MemberVO;
import com.th.message.dao.MessageDao;
import com.th.message.vo.MessageVO;

public class PreventSessionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MessageDao messageDao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return true;
	}
	
	@Override // (09.19.수) postHandle은 "Controller한 이후" 이다.
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		// 세션이 있으면 계속 갱신되어라!
		MemberVO sessionMemberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		List<MessageVO> messageList = (List<MessageVO>) session.getAttribute(Session.MESSAGE);
		
		if( sessionMemberVO != null) {
			MemberVO memberVO = memberDao.selectOneMember(sessionMemberVO);
			session.setAttribute(Session.MEMBER, memberVO);
			
			if(messageList != null) {
				List<MessageVO> messageVOList = messageDao.selectMessageList(memberVO.getEmail());
				session.setAttribute(Session.MESSAGE, messageVOList);
			}
		}
		
	}
}
