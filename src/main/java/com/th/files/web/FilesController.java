package com.th.files.web;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.th.common.web.DownloadUtil;
import com.th.files.service.FilesService;
import com.th.files.vo.FilesVO;

@Controller
public class FilesController {
	
	@Autowired
	private FilesService filesService;
	
	//@Value("${C:/Users/YEAH/Documents/uploadFiles}")
	private String uploadPath = "C:Users/YEAH/Documents/uploadFiles";
	
	/*@RequestMapping("/board/{boardId}/{articleId}/download/{fileId}")
	public void fileDownload(@PathVariable int boardId, @PathVariable String articleId, @PathVariable String fileId
							 , HttpServletRequest request, HttpServletResponse response) {
		
		FilesVO fileVO = this.filesService.readOneFile(boardId, articleId, fileId);
		
		String originFileName = fileVO.getOriginFileName();
		String fileName = fileVO.getFileName();
		try {
			new DownloadUtil(this.uploadPath + File.separator + fileName).download(request, response, originFileName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}*/

}
