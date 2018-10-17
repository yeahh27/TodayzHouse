package com.th.admin.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.admin.dao.AdminDao;

@Component
public class AdminBizImpl implements AdminBiz {
	
	@Autowired
	private AdminDao adminDao;

}
