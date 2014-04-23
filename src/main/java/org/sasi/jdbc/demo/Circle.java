package org.sasi.jdbc.demo;

public class Circle {
	
	public int id;
	public String name;
	
	public Circle(int id, String  name){
		setId(id);
		setName(name);
	}
	public Circle() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
