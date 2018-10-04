package com.th.files.biz;

import java.util.List;
import java.util.Map;

import com.th.files.vo.FilesVO;

public interface FilesBiz {

	public int insertFile(FilesVO filesVO);
	
	public List<FilesVO> selectAllFiles(int boardId, String articleId);		//boardId, articleId
	
	public FilesVO selectOneFile(int boardId, String articleId, String fileId);	//boardId, articleId, fileId

	public int updateFile(FilesVO filesVO);
	
	public int deleteOneFile(int boardId, String articleId, String fileId);		//boardId, articleId, fileId
	
	public int deleteFilesByArticle(int boardId, String articleId);
}
