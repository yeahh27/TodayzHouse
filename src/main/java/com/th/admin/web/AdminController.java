package com.th.admin.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.th.admin.service.AdminService;
import com.th.article.vo.ArticleVO;
import com.th.common.session.Session;
import com.th.member.vo.MemberVO;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/{no}")
	private ModelAndView viewAdminLayoutPage(@PathVariable int no) {
		ModelAndView view = new ModelAndView();
		
		if(no == 1) {
			List<ArticleVO> warnArticleList = this.adminService.selectArticlesByAdmin();
			view.setViewName("/admin/report");
			view.addObject("warnArticleList", warnArticleList);
		} else {
			List<MemberVO> warnMemberList = this.adminService.selectMembersByAdmin();
			view.setViewName("/admin/member");
			view.addObject("warnMemberList", warnMemberList);
		}
		
		return view;
	}
	
	@GetMapping("/admin/{boardId}/articleDelete/{articleId}")
	@ResponseBody
	public boolean doArticleDeleteAction(@PathVariable int boardId, @PathVariable String articleId, HttpSession session) {
		return this.adminService.updateDeleteYN(boardId, articleId);
	}

}
