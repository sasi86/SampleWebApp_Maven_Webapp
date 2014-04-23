package org.sasi.jdbc.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class RunCircle {
	
	
	
	private static CircleJdbcDemoImpl circleJdbcDemoImpl;
	private static CircleSimpleJdbcDemoImpl circleSimpleJdbcDemoImpl;
	private static CircleNamedParameterJdbcDemoImpl circleNamedParameterJdbcDemoImpl;

	public CircleNamedParameterJdbcDemoImpl getCircleNamedParameterJdbcDemoImpl() {
		return circleNamedParameterJdbcDemoImpl;
	}
	@Autowired(required=true)
	public void setCircleNamedParameterJdbcDemoImpl(
			CircleNamedParameterJdbcDemoImpl circleNamedParameterJdbcDemoImpl) {
		RunCircle.circleNamedParameterJdbcDemoImpl = circleNamedParameterJdbcDemoImpl;
	}
	public CircleSimpleJdbcDemoImpl getCircleSimpleJdbcDemoImpl() {
		return circleSimpleJdbcDemoImpl;
	}
	@Autowired(required=true)
	public void setCircleSimpleJdbcDemoImpl(
			CircleSimpleJdbcDemoImpl circleSimpleJdbcDemoImpl) {
		RunCircle.circleSimpleJdbcDemoImpl = circleSimpleJdbcDemoImpl;
	}

	public static CircleJdbcDemoImpl getCircleJdbcDemoImpl() {
		return circleJdbcDemoImpl;
	}

	@Autowired(required=true)
	public void setCircleJdbcDemoImpl(CircleJdbcDemoImpl circleJdbcDemoImpl) {
		RunCircle.circleJdbcDemoImpl = circleJdbcDemoImpl;
	}

	public static void main(String[] args) {
		Circle c=null;
		int circleId = 2;
		String circleName= "First Circle";
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("applicationContext.xml"));
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//CircleJdbcDemoImpl dao = ctx.getBean("circleJdbcDemoImpl" , CircleJdbcDemoImpl.class);
		
		//circleJdbcDemoImpl.insertIntoLable();//createNewTable();
		System.out.println(circleJdbcDemoImpl.getLabel("fName"));
		List<Label> labels = circleJdbcDemoImpl.getAllLabel();
		for(Label label : labels){
			System.out.println(label.getLableName() + " : "+ label.getVal());
		}
		
		/*circleJdbcDemoImpl.insertNewCircle(new Circle(3, "Third Circle"));
		
		int count = circleJdbcDemoImpl.getCircleCount();
		System.out.println("Total No of Circles : "+ count);
		long id = circleJdbcDemoImpl.getCircleID(circleName);
		System.out.println(circleName+" ID :"+id);
		String name = circleJdbcDemoImpl.getCircleName(circleId);
		System.out.println("Circle Name With ID "+circleId+" is : "+ name);
		c =  circleJdbcDemoImpl.getCircleInfo(circleId);
		System.out.println("Circle ID :"+c.getId()+" Circle Name:"+ c.getName());
		List<Circle> allCircles = circleJdbcDemoImpl.getAllCircle();
		System.out.println("Circle count : "+allCircles.size());
		
		System.out.println("===================================================");
		System.out.println("Using SimpleJdbcTemplate");
		System.out.println("===================================================");
		
		int count1 = circleSimpleJdbcDemoImpl.getCircleCount();
		System.out.println("Total No of Circles : "+ count1);
		long id1 = circleSimpleJdbcDemoImpl.getCircleID(circleName);
		System.out.println(circleName+" ID :"+id1);
		String name1 = circleSimpleJdbcDemoImpl.getCircleName(circleId);
		System.out.println("Circle Name With ID "+circleId+" is : "+ name1);
		c =  circleSimpleJdbcDemoImpl.getCircleInfo(circleId);
		System.out.println("Circle ID :"+c.getId()+" Circle Name:"+ c.getName());
		List<Circle> allCircles1 = circleSimpleJdbcDemoImpl.getAllCircle();
		System.out.println("Circle count : "+allCircles1.size());
		
		System.out.println("===================================================");
		System.out.println("Using NamedParameterJdbcTemplate");
		System.out.println("===================================================");
		
		
		long id2 = circleNamedParameterJdbcDemoImpl.getCircleID(circleName);
		System.out.println(circleName+" ID :"+id2);
		String name2 = circleNamedParameterJdbcDemoImpl.getCircleName(circleId);
		System.out.println("Circle Name With ID "+circleId+" is : "+ name2);
		c =  circleNamedParameterJdbcDemoImpl.getCircleInfo(circleId);
		System.out.println("Circle ID :"+c.getId()+" Circle Name:"+ c.getName());
		List<Circle> allCircles2 = circleNamedParameterJdbcDemoImpl.getAllCircle();
		System.out.println("Circle count : "+allCircles2.size());*/
	}
	
	
}
