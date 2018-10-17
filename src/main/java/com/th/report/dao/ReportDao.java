package com.th.report.dao;

import java.util.List;
import java.util.Map;

import com.th.report.vo.ReportVO;

public interface ReportDao {

	public int insertReport(ReportVO reportVO);

	public int selectReportByArticle(Map<String, Object> param);

	public int deleteReport(ReportVO reportVO);

	public int deleteAllReportsByArticle(Map<String, Object> param);

	public int selectReportCountByArticle(Map<String, Object> param);
	
	public List<ReportVO> selectReportByEmail(String email);
}
