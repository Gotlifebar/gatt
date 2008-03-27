package org.gatt.ui.dialogs.helper;

import org.gatt.domain.Room;

/**
 * @author Chucho
 * Class that wraps the information of a room object
 */
public class RoomWrapper{
	/**
	 * the Room object
	 */
	private Room room;
	
	/**
	 * constructor
	 * @param room
	 */
	public RoomWrapper(Room room){
		this.room = room;
	}
	
	/**
	 * return the Room object
	 */
	public Room getRoom(){
		return room;
	}
	
	/** 
	 * return the number of the room as a string
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return room.getNumber();
	}
	
}
