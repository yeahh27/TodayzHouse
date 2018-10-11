package com.th.report.vo;

public class ReportVO {

	private String reportId;
	private int boardId;
	private String articleId;
	private String email;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ReportVO [reportId=" + reportId + ", boardId=" + boardId + ", articleId=" + articleId + ", email="
				+ email + "]";
	}

}
