package com.jt.service.sso.main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jt.facade.sso.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-context.xml") 
@Transactional
public class TestDemo{

	@Autowired
	private UserService userService;
	
	@Test
	public void test1(){
		
	}
}
