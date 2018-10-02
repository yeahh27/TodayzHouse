package com.th.files.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.th.files.service.FilesService;

@Controller
public class FilesController {
	
	@Autowired
	private FilesService filesService;

}
