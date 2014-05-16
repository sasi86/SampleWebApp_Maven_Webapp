package org.sasi.spring.mvc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="States")
public class State {
	
	private int stateId;
	private String stateName;
	
	public int getStateId() {
		return stateId;
	}
	
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	

}
