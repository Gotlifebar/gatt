package org.gatt.domain;

public class Hour extends DomainObject {
	
	private int time;
	private String representation;
	
	public Hour(){
		super();
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getRepresentation() {
		return representation;
	}
	public void setRepresentation(String representation) {
		this.representation = representation;
	}
	
	
}
