package org.gatt.domain;

public class Room extends DomainObject {
	
	private String number;
	private int capacity;
	private String type;	

	public Room(){
		super();
	}
	
	public String getNumber(){
		return this.number;
	}
	public int getCapacity(){
		return this.capacity;
	}
	public String getType(){
		return this.type;
	}	

	public void setNumber(String number){
		this.number = number;
	}
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	public void setType(String type){
		this.type = type;
	}
}
