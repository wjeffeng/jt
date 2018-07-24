package com.jt.manage.service;

import org.springframework.stereotype.Service;

import com.jt.common.spring.exetend.PropertyConfig;

@Service
public class PicUpLoadService {
	
	@PropertyConfig
	public String UPLOAD_DIR;
	@PropertyConfig
	public String IMAGE_BASE_URL;
}
