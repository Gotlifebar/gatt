package org.gatt.domain.factories;
import java.util.Collection;

import org.gatt.domain.Room;

public interface RoomDAO {
	public Room findRoom(int id);
	public Collection<Room> findAll();
	public int countRooms();
	public void randomizeMedia(double p);
}
