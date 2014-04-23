package org.sasi.sample.simple;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.sasi.jdbc.demo.CircleJdbcDemoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//Sasi K palani
@Component
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CircleJdbcDemoImpl circleJdbcDemoImpl;

	public CircleJdbcDemoImpl getCircleJdbcDemoImpl() {
		return circleJdbcDemoImpl;
	}
	
	public void setCircleJdbcDemoImpl(CircleJdbcDemoImpl circleJdbcDemoImpl) {
		this.circleJdbcDemoImpl = circleJdbcDemoImpl;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSayWelcome() {
		// check if null?
		System.out.println(circleJdbcDemoImpl.getLabel("fName"));
		if ("".equals(name) || name == null) {
			return "";
		} else {
			return "Ajax message : Welcome " + name;
		}
	}
}
