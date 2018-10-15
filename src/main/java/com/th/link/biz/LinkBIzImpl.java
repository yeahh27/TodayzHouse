package com.th.link.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.link.dao.LinkDao;
import com.th.link.vo.LinkVO;

@Component
public class LinkBIzImpl implements LinkBiz {
	
	@Autowired
	private LinkDao linkDao;

	@Override
	public int insertLink(LinkVO linkVO) {
		return this.linkDao.insertLink(linkVO);
	}

	@Override
	public List<LinkVO> selectAllLinksByArticle(String fileId) {
		return this.linkDao.selectAllLinksByArticle(fileId);
	}

	@Override
	public LinkVO selectOneLink(String fileId, String linkId) {
		Map<String, Object> param = new HashMap<>();
		param.put("fileId", fileId);
		param.put("linkId", linkId);
		
		return this.linkDao.selectOneLink(param);
	}

	@Override
	public int deleteOneLink(String linkId) {
		return this.linkDao.deleteOneLink(linkId);
	}

	@Override
	public int updateOneLink(LinkVO linkVO) {
		return this.linkDao.updateOneLink(linkVO);
	}

}
