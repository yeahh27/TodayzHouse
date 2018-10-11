package com.th.report.biz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.report.dao.ReportDao;
import com.th.report.vo.ReportVO;

@Component
public class ReportBizImpl implements ReportBiz {
	
	@Autowired
	private ReportDao reportDao;

	@Override
	public int insertReport(ReportVO reportVO) {
		return this.reportDao.insertReport(reportVO);
	}

	@Override
	public int selectReportByArticle(int boardId, String articleId, String email) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		param.put("email", email);

		return this.reportDao.selectReportByArticle(param);
	}

	@Override
	public int deleteReport(ReportVO reportVO) {
		return this.reportDao.deleteReport(reportVO);
	}

	@Override
	public int deleteAllReportsByArticle(int boardId, String articleId) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		
		return this.reportDao.deleteAllReportsByArticle(param);
	}

	@Override
	public int selectReportCountByArticle(int boardId, String articleId) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardId", boardId);
		param.put("articleId", articleId);
		
		return this.reportDao.selectReportCountByArticle(param);
	}

	

}
