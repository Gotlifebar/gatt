package org.gatt.domain;

public class Session extends DomainObject {
	
	private Group group;
	private Hour hour;
	private Room room;
	private boolean isTheorical;
	private int usedHours;
	
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
	public boolean getIsTheorical() {
		return isTheorical;
	}

	public void setIsTheorical(boolean isTheorical) {
		this.isTheorical = isTheorical;
	}

	public int getUsedHours() {
		return usedHours;
	}

	public void setUsedHours(int usedHours) {
		this.usedHours = usedHours;
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
