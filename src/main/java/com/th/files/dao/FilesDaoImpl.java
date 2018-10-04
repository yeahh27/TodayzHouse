package com.th.files.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.files.vo.FilesVO;

@Repository
public class FilesDaoImpl extends SqlSessionDaoSupport implements FilesDao {
	
	private Logger logger = LoggerFactory.getLogger(FilesDaoImpl.class);

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("MyBatis Init");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertFile(FilesVO filesVO) {
		return getSqlSession().insert("FilesDao.insertFile", filesVO);
	}

	@Override
	public List<FilesVO> selectAllFiles(Map<String, Object> param) {
		return getSqlSession().selectList("FilesDao.selectAllFiles", param);
	}

	@Override
	public FilesVO selectOneFile(Map<String, Object> param) {
		return getSqlSession().selectOne("FilesDao.selectOneFile", param);
	}

	@Override
	public int updateFile(FilesVO filesVO) {
		return getSqlSession().update("FilesDao.updateFile", filesVO);
	}

	@Override
	public int deleteOneFile(Map<String, Object> param) {
		return getSqlSession().delete("FilesDao.deleteOneFile", param);
	}
	
	@Override
	public int deleteFilesByArticle(Map<String, Object> param) {
		return getSqlSession().delete("FilesDao.deleteFilesByArticle", param);
	}
}
