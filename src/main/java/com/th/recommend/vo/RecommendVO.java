package com.th.recommend.vo;

public class RecommendVO {

	private String recommendId;
	private int boardId;
	private String articleId;
	private String email;

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
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
		return "RecommendVO [recommendId=" + recommendId + ", boardId=" + boardId + ", articleId=" + articleId
				+ ", email=" + email + "]";
	}

}
