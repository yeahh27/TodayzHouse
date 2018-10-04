package com.th.files.service;

import java.util.List;

import com.th.files.vo.FilesVO;

public interface FilesService {
	
	public List<FilesVO> readAllFiles(int boardId, String articleId);		//boardId, articleId
	
	public FilesVO readOneFile(int boardId, String articleId, String fileId);	//boardId, articleId, fileId
}
