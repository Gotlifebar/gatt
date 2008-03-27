package org.gatt.domain;

/**
 * @author Chucho
 * Session class
 */
public class Session extends DomainObject {
	
	/**
	 * Group object
	 */
	private Group group;
	/**
	 * Hour object
	 */
	private Hour hour;
	/**
	 * Room object
	 */
	private Room room;
	/**
	 * indicates whether the section is theorical or not
	 */
	private boolean isTheorical;
	
	/**
	 * Indicates the number of hour used by the session
	 */
	private int usedHours;
	
	/**
	 * constructor
	 */
	public Session(){
		super();
	}
	
	/**
	 * return the group
	 */
	public Group getGroup() {
		return group;
	}
	/**
	 * sets the group
	 * @param group
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
	/**
	 * return the hour
	 */
	public Hour getHour() {
		return hour;
	}
	/**
	 * return true if the session is theorical, false otherwise
	 */
	public boolean getIsTheorical() {
		return isTheorical;
	}

	/**
	 * sets if the session is theorical
	 * @param isTheorical
	 */
	public void setIsTheorical(boolean isTheorical) {
		this.isTheorical = isTheorical;
	}

	/**
	 * return the number of used hours
	 */
	public int getUsedHours() {
		return usedHours;
	}

	/**
	 * sets the number of used hours
	 * @param usedHours
	 */
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
