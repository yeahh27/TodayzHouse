package com.th.files.dao;

import java.util.List;
import java.util.Map;

import com.th.files.vo.FilesVO;

public interface FilesDao {

	public int insertFile(FilesVO filesVO);
	
	public List<FilesVO> selectAllFiles(Map<String, Object> param);		//boardId, articleId
	
	public FilesVO selectOneFile(Map<String, Object> param);	//boardId, articleId, fileId

	public int updateArticle(FilesVO filesVO);
	
	public int deleteOneArticle(Map<String, Object> param);		//boardId, articleId, fileId
}
