package com.th.report.biz;

import java.util.List;

import com.th.report.vo.ReportVO;

public interface ReportBiz {

	public int insertReport(ReportVO reportVO);
	
	public int selectReportByArticle(int boardId, String articleId, String email);
	
	public int deleteReport(ReportVO reportVO);
	
	public int deleteAllReportsByArticle(int boardId, String articleId);
	
	public int selectReportCountByArticle(int boardId, String articleId);
	
	public List<ReportVO> selectReportByEmail(String email);
}
