package com.th.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.files.biz.FilesBiz;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesBiz filesBiz;
}
