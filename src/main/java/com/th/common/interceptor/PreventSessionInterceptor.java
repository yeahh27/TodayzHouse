package com.th.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.th.common.session.Session;
import com.th.member.vo.MemberVO;

public class PreventSessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute(Session.MEMBER);
		
		if(memberVO != null) {
			response.sendRedirect("TodayzHouse/");
			return false;
		}
		return true;
	}
}
