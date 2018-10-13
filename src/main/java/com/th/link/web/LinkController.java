package com.th.link.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.th.link.service.LinkService;
import com.th.link.vo.LinkVO;

@Controller
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	@PostMapping("/link/write")
	@ResponseBody
	public Map<String, Object> doLinkWriteAction (@Valid @ModelAttribute LinkVO linkVO, Errors errors) {
		
		System.out.println("asdas" + linkVO.toString());
		
		Map<String, Object> result = new HashMap<>();
		
		boolean isSuccess = this.linkService.createLink(linkVO);
		
		if(isSuccess) {
			result.put("status", "ok");
		} else {
			result.put("status", "fail");
		}
		
		return result;
	}

}
