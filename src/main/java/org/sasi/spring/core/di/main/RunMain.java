package org.sasi.spring.core.di.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("di.xml");
		Triangle triangle = context.getBean("triangle", Triangle.class);
		triangle.draw();

	}

}
