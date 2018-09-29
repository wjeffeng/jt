package com.jt.facade.manage.service.impl;

import org.springframework.stereotype.Service;

import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.facade.manage.service.PicUpLoadService;

@Service("picUpLoadService")
public class PicUpLoadServiceImpl extends PicUpLoadService{
	
	@PropertyConfig
	public String UPLOAD_DIR;
	@PropertyConfig
	public String IMAGE_BASE_URL;
}
