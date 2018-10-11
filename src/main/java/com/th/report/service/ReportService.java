package com.th.report.service;

import com.th.report.vo.ReportVO;

public interface ReportService {

	public boolean createReport(ReportVO reportVO);
	
	public boolean deleteReport(ReportVO reportVO);
	
	public int readReportCount(int boardId, String articleId);
}
