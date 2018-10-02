package com.th.member.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.th.common.session.Session;
import com.th.member.service.MemberService;
import com.th.member.validator.MemberValidator;
import com.th.member.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String viewHomepage() {
		return "home";
	}
	
	@GetMapping("/member/regist")
	public String viewMemberRegistPage() {
		return "member/regist";
	}
	
	@PostMapping("/member/regist")
	@ResponseBody
	public Map<String, Object> doMemberRegistAction(@Validated({MemberValidator.Regist.class}) @ModelAttribute MemberVO memberVO, Errors errors) {
		Map<String, Object> result = new HashMap<>();
		if(errors.hasErrors()) {
			result.put("message", errors);
			result.put("status", false);
			return result;
		}
		
		this.memberService.registMember(memberVO);
		result.put("status", true);
		
		return result;
	}
	
	@PostMapping("/member/check/duplicate")
	@ResponseBody
	public Map<String, Object> doCheckDuplicateEmail(@RequestParam String email) {
		boolean isDuplicate = this.memberService.isDuplicateEmail(email);
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", "OK");
		result.put("duplicated", isDuplicate);
		
		return result;
	}

	@GetMapping("/member/login")
	public String viewMemberLoginPage() {
		return "member/login";
	}
	
	@PostMapping("/member/login")
	@ResponseBody
	public Map<String, Object> doMemberLoginAction(@Validated({MemberValidator.Login.class}) @ModelAttribute MemberVO memberVO
													, Errors errors, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		if(errors.hasErrors()) {
			result.put("message", errors);
			result.put("status", false);
			return result;
		}
		
		boolean isBlockAccount = this.memberService.isBlockUser(memberVO.getEmail());
		boolean isLoginSuccess = this.memberService.loginMember(memberVO, session);
		
		if(!isBlockAccount) {
			
			if(!isLoginSuccess) {
				this.memberService.increaseLoginFailCount(memberVO.getEmail());
				result.put("status", false);
			} else {
				this.memberService.unblockUser(memberVO.getEmail());
				result.put("status", true);
			}
		} else {
			result.put("status", false);
		}
		
		if(isLoginSuccess) {
			String token = UUID.randomUUID().toString();
			session.setAttribute(Session.CSRF_TOKEN, token);
		}
		
		return result;
	}
	
	@GetMapping("/member/logout")
	public String doMemberLogoutAction(HttpSession session) {
		session.invalidate();	
		return "redirect:/member/login";
	}
}
