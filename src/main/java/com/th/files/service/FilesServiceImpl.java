package com.th.files.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.files.biz.FilesBiz;
import com.th.files.vo.FilesVO;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesBiz filesBiz;

	@Override
	public List<FilesVO> readAllFiles(int boardId, String articleId) {
		return this.filesBiz.selectAllFiles(boardId, articleId);
	}

	@Override
	public FilesVO readOneFile(int boardId, String articleId, String fileId) {
		return this.filesBiz.selectOneFile(boardId, articleId, fileId);
	}

	
}
