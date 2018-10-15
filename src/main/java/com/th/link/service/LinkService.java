package com.th.link.service;

import com.th.link.vo.LinkVO;

public interface LinkService {

	public boolean createLink(LinkVO linkVO);
	
	public boolean deleteOneLink(String linkId);
	
	public boolean updateOneLink(LinkVO linkVO);
}
