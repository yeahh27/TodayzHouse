package com.th.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.th.common.kafka.KafkaLogger;
import com.th.common.session.Session;
import com.th.member.vo.MemberVO;

public class LogInterceptor extends HandlerInterceptorAdapter {

	private KafkaLogger qosTimebaseLogger = new KafkaLogger();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String ip = request.getRemoteAddr();
		
		HttpSession session = request.getSession();
		String id = "";
		
		if(session.getAttribute(Session.MEMBER) != null) {
			MemberVO memberVO = (MemberVO)session.getAttribute(Session.MEMBER);
			id = memberVO.getEmail();
		}
		
		String url = request.getRequestURI();
		String method = request.getMethod();
		
		qosTimebaseLogger.info("#" + ip + "#" + id + "#" + url + "#" + method);
		
		return true;
		
	}
}
