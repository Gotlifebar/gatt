package org.gatt.ui.dialogs.helper;

import org.gatt.domain.Room;

public class RoomWrapper{
	private Room room;
	
	public RoomWrapper(Room room){
		this.room = room;
	}
	
	public Room getRoom(){
		return room;
	}
	
	public String toString(){
		return room.getSpace();
	}
	
}
