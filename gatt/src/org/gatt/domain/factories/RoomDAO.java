package org.gatt.domain.factories;
import org.gatt.domain.*;

public interface RoomDAO {
	public Room findRoom(int id);
	public int countRooms();
}
