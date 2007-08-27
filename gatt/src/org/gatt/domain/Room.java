package org.gatt.domain;

public class Room extends DomainObject {
	
	private String space;
	private int maxChairs;
	private int currentChairs;
	private String type;
	
	public Room(){
		super();
	}
	
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public int getMaxChairs() {
		return maxChairs;
	}
	public void setMaxChairs(int maxChairs) {
		this.maxChairs = maxChairs;
	}
	public int getCurrentChairs() {
		return currentChairs;
	}
	public void setCurrentChairs(int currentChairs) {
		this.currentChairs = currentChairs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
