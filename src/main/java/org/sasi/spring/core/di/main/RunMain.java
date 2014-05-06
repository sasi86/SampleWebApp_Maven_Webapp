package org.sasi.spring.core.di.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunMain {

	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		/*ApplicationContext context = new ClassPathXmlApplicationContext("di.xml");
		Triangle triangle = context.getBean("triangle", Triangle.class);
		triangle.draw();*/
		System.out.println(Singleton.getInstance());
		System.out.println(Singleton.getInstance().clone());
		
	}

}
