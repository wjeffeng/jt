package com.jt.manage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/{pageName}")
	public String pageName(@PathVariable String pageName){
		return pageName;
	}
}
