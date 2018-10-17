package com.th.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.article.biz.ArticleBiz;
import com.th.report.biz.ReportBiz;
import com.th.report.vo.ReportVO;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportBiz reportBiz;
	
	@Autowired
	private ArticleBiz articleBiz;
	
	@Override
	public boolean createReport(ReportVO reportVO) {
		boolean isSuccess = this.reportBiz.insertReport(reportVO) > 0;
		if(isSuccess) {
			this.articleBiz.updateReport(reportVO.getBoardId(), reportVO.getArticleId(), this.reportBiz.selectReportCountByArticle(reportVO.getBoardId(), reportVO.getArticleId()));
		}
		return isSuccess;
	}

	@Override
	public boolean deleteReport(ReportVO reportVO) {
		boolean isSuccess = this.reportBiz.deleteReport(reportVO) > 0;
		if(isSuccess) {
			this.articleBiz.updateReport(reportVO.getBoardId(), reportVO.getArticleId(), this.reportBiz.selectReportCountByArticle(reportVO.getBoardId(), reportVO.getArticleId()));
		}
		return isSuccess;
	}

	@Override
	public int readReportCount(int boardId, String articleId) {
		return this.reportBiz.selectReportCountByArticle(boardId, articleId);
	}

}
