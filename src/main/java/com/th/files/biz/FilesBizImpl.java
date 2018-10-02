package com.th.files.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.files.dao.FilesDao;
import com.th.files.vo.FilesVO;

@Component
public class FilesBizImpl implements FilesBiz {
	
	@Autowired
	private FilesDao filesDao;

	@Override
	public int insertFile(FilesVO filesVO) {
		return this.filesDao.insertFile(filesVO);
	}

	@Override
	public List<FilesVO> selectAllFiles(int boardId, String articleId) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		
		return this.filesDao.selectAllFiles(param);
	}

	@Override
	public FilesVO selectOneFile(int boardId, String articleId, String fileId) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		param.put("fileId", fileId);
		
		return this.filesDao.selectOneFile(param);
	}

	@Override
	public int updateArticle(FilesVO filesVO) {
		return this.filesDao.updateArticle(filesVO);
	}

	@Override
	public int deleteOneArticle(int boardId, String articleId, String fileId) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		param.put("fileId", fileId);
		
		return this.filesDao.deleteOneArticle(param);
	}

}
