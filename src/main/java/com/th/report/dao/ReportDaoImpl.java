package com.th.report.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.th.report.vo.ReportVO;

@Repository
public class ReportDaoImpl extends SqlSessionDaoSupport implements ReportDao {
	
	private Logger logger = LoggerFactory.getLogger(ReportDaoImpl.class);
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Init MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertReport(ReportVO reportVO) {
		return this.getSqlSession().insert("ReportDao.insertReport", reportVO);
	}

	@Override
	public int selectReportByArticle(Map<String, Object> param) {
		return this.getSqlSession().selectOne("ReportDao.selectReportByArticle", param);
	}

	@Override
	public int deleteReport(ReportVO reportVO) {
		return this.getSqlSession().delete("ReportDao.deleteReport", reportVO);
	}

	@Override
	public int deleteAllReportsByArticle(Map<String, Object> param) {
		return this.getSqlSession().delete("ReportDao.deleteAllReportsByArticle", param);
	}

	@Override
	public int selectReportCountByArticle(Map<String, Object> param) {
		return this.getSqlSession().selectOne("ReportDao.selectReportCountByArticle", param);
	}


}
