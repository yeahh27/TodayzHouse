package com.th.files.vo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class FilesVO {

	private String fileId;
	@Value("F_BOARD_ID")
	private int boardId;
	@Value("F_ARTICLE_ID")
	private String articleId;
	private String idx;
	private String originFileName;
	private String fileName;
	private String content;

	private MultipartFile file;
	private List<MultipartFile> fileList;

	public FilesVO() {
		this.fileName = "";
		this.originFileName = "";
		this.fileList = null;
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

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<MultipartFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}

	@Override
	public String toString() {
		return "FilesVO [fileId=" + fileId + ", boardId=" + boardId + ", articleId=" + articleId + ", idx=" + idx
				+ ", originFileName=" + originFileName + ", fileName=" + fileName + ", content=" + content + ", file="
				+ file + ", fileList=" + fileList + "]";
	}

}
