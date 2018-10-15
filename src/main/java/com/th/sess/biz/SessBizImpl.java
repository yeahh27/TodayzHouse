package com.th.sess.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.sess.dao.SessDao;

@Component
public class SessBizImpl implements SessBiz {

	@Autowired
	private SessDao sessDao;
	
}
