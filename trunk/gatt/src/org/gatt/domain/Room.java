package org.gatt.domain;

public class Room extends DomainObject {
	
	private String space;
	private int maxChairs;
	private int currentChairs;
	private int type;
	
	public static int NORMAL = 1;
	public static int SPECIAL = 2;
	public static int AUDITORIUM = 3;
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
