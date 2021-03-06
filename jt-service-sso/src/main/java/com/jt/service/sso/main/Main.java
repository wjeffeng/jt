package com.jt.service.sso.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class);
	private static volatile boolean running = true;
	private static final String PATH = "classpath:spring/spring-context.xml";

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext(PATH);
			context.start();
			log.info("[SsoService启动...]");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		synchronized (Main.class) {
			while (running) {
				try {
					Main.class.wait();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		context.close();
	}
}
