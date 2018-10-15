package com.th.link.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.th.common.session.Session;
import com.th.link.service.LinkService;
import com.th.link.vo.LinkVO;

@Controller
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	@PostMapping("/link/write")
	@ResponseBody
	public Map<String, Object> doLinkWriteAction (@Valid @ModelAttribute LinkVO linkVO, Errors errors, 
												  @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		
		if(!linkVO.getToken().equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		Map<String, Object> result = new HashMap<>();
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		linkVO.setProduct(filter.doFilter(linkVO.getProduct()));
		linkVO.setAddress(filter.doFilter(linkVO.getAddress()));
		
		boolean isSuccess = this.linkService.createLink(linkVO);
		
		if(isSuccess) {
			result.put("status", "ok");
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}
	
	@GetMapping("/link/delete/{linkId}")
	@ResponseBody
	public boolean doALinkDeleteAction(@PathVariable String linkId, @RequestParam String token
			  						   , @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		
		if(!token.equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		return this.linkService.deleteOneLink(linkId);
	}
	
	@PostMapping("/link/modify/{linkId}")
	@ResponseBody
	public boolean doALinkModifyAction(@PathVariable String linkId, @ModelAttribute LinkVO linkVO
			  						   , @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		
		if(!linkVO.getToken().equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		linkVO.setLinkId(linkId);
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		linkVO.setProduct(filter.doFilter(linkVO.getProduct()));
		linkVO.setAddress(filter.doFilter(linkVO.getAddress()));
		
		return this.linkService.updateOneLink(linkVO);
	}

}
