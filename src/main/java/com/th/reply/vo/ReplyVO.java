package com.th.reply.vo;

public class ReplyVO {

	private int level;
	private String replyId;
	private int boardId;
	private String articleId;
	private String email;
	private String content;
	private int recommend;
	private String regDate;
	private String parentId;

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

	@Override
	public String toString() {
		return "ReplyVO [level=" + level + ", replyId=" + replyId + ", boardId=" + boardId + ", articleId=" + articleId
				+ ", email=" + email + ", content=" + content + ", recommend=" + recommend + ", regDate=" + regDate
				+ ", parentId=" + parentId + "]";
	}

}
