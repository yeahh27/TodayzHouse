package com.th.reply.vo;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;

import com.th.member.vo.MemberVO;

public class ReplyVO {

	private int level;
	private String replyId;
	private int boardId;
	private String articleId;
	private String email;
	@NotEmpty(message = "내용을 입력하세요.")
	private String content;
	private int recommend;
	private String regDate;
	private String parentId;
	private String deleteYn;

	private MemberVO memberVO;

	private String token;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "ReplyVO [level=" + level + ", replyId=" + replyId + ", boardId=" + boardId + ", articleId=" + articleId
				+ ", email=" + email + ", content=" + content + ", recommend=" + recommend + ", regDate=" + regDate
				+ ", parentId=" + parentId + ", deleteYn=" + deleteYn + ", memberVO=" + memberVO + ", token=" + token
				+ "]";
	}

}
