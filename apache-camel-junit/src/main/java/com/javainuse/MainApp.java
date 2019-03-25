package com.javainuse;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext springCtx = new ClassPathXmlApplicationContext("camel-context.xml");

		CamelContext ctx = springCtx.getBean("simpleRouterContext", CamelContext.class);

		try {
			ctx.start();
			Thread.sleep(5 * 60 * 1000);
			ctx.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}