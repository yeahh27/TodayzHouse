package com.th.article.vo;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;

import com.th.files.vo.FilesVO;
import com.th.member.vo.MemberVO;

public class ArticleVO {

	@Value("A_ARTICLE_ID")
	private String articleId;
	@Value("A_BOARD_ID")
	private int boardId;
	@NotEmpty(message = "제목은 필수 입력 값입니다.")
	private String title;

	@Value("A_EMAIL")
	private String email;
	private String regDate;
	private String modifyDate;
	private int viewCount;
	private int recommend;
	private int report;

	private MemberVO memberVO;
	private FilesVO fileVO;

	private String token;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public FilesVO getFileVO() {
		return fileVO;
	}

	public void setFileVO(FilesVO fileVO) {
		this.fileVO = fileVO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "ArticleVO [articleId=" + articleId + ", boardId=" + boardId + ", title=" + title + ", email=" + email
				+ ", regDate=" + regDate + ", modifyDate=" + modifyDate + ", viewCount=" + viewCount + ", recommend="
				+ recommend + ", report=" + report + ", memberVO=" + memberVO + ", fileVO=" + fileVO
				+ ", token=" + token + "]";
	}

}
