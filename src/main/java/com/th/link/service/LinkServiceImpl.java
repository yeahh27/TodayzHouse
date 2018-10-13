package com.th.link.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.link.biz.LinkBiz;
import com.th.link.vo.LinkVO;

@Service
public class LinkServiceImpl implements LinkService{
	
	@Autowired
	private LinkBiz linkBiz;

	@Override
	public boolean createLink(LinkVO linkVO) {
		return this.linkBiz.insertLink(linkVO) > 0;
	}

}
