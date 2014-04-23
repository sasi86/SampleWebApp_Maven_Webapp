package org.sasi.jdbc.demo;

public class Label {
	
	public String lableName;
	public String val;
	
	public Label(String lableName, String  val){
		setLableName(lableName);
		setVal(val);
	}
	public Label() {
		
	}
	public String getLableName() {
		return lableName;
	}
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	

}
