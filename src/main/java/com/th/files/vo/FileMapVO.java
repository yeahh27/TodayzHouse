package com.th.files.vo;

import java.util.Map;

public class FileMapVO {

	private Map<String, FilesVO> fileMap;

	public Map<String, FilesVO> getFileMap() {
		return fileMap;
	}

	public void setFileMap(Map<String, FilesVO> fileMap) {
		this.fileMap = fileMap;
	}

	@Override
	public String toString() {
		return "FileMapVO [fileMap=" + fileMap + "]";
	}
	
	

}
