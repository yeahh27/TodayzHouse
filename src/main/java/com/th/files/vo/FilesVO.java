package com.th.files.vo;

public class FilesVO {

	private String fileId;
	private int boardId;
	private String articleId;
	private String originFileName;
	private String fileName;
	private String content;

	public FilesVO() {
		this.fileName = "";
		this.originFileName = "";
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "FileVO [fileId=" + fileId + ", boardId=" + boardId + ", articleId=" + articleId + ", originFileName="
				+ originFileName + ", fileName=" + fileName + ", content=" + content + "]";
	}

}
