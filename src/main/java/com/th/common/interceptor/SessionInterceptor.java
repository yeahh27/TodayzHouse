package com.th.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.th.common.session.Session;
import com.th.member.vo.MemberVO;


public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	// handler ==> Controller
			throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute(Session.MEMBER);
		
		if(member == null) {
			response.sendRedirect("/TodayzHouse/member/login");
			return false;
		}
		
		return true;
	}
	
}
