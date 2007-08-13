package org.gatt.domain;

public class Session extends DomainObject {
	
	private Group group;
	private Hour hour;
	private Room room;
	
	public Session(){
		super();
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Hour getHour() {
		return hour;
	}
	public void setHour(Hour hour) {
		this.hour = hour;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	
}
