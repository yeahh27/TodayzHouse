package com.th.link.dao;

import java.util.List;
import java.util.Map;

import com.th.link.vo.LinkVO;

public interface LinkDao {

	public int insertLink(LinkVO linkVO);
	
	public List<LinkVO> selectAllLinksByArticle(String fileId);
	
	public LinkVO selectOneLink(Map<String, Object> param);
}
