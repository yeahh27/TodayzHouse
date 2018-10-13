package com.th.link.biz;

import java.util.List;

import com.th.link.vo.LinkVO;

public interface LinkBiz {

	public int insertLink(LinkVO linkVO);
	
	public List<LinkVO> selectAllLinksByArticle(String fileId);
	
	public LinkVO selectOneLink(String fileId, String linkId);
}
