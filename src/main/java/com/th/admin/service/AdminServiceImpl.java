package com.th.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.admin.biz.AdminBiz;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminBiz adminBiz;

}
