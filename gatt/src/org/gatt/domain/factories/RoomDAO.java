package org.gatt.domain.factories;
import java.util.Collection;

import org.gatt.domain.Room;

/**
 * @author david
 * Room DAO interface
 */
public interface RoomDAO {
	/**
	 * @param id
	 * Perform the query to find an Room in the database
	 */
	public Room findRoom(int id);
	
	/**
	 * Perform the query to find all Rooms in the database
	 */
	public Collection<Room> findAll();
	/**
	 * return the number of rooms in the database
	 */
	public int countRooms();
	/**
	 * randomly sets the value that indicates if has media
	 * @param p
	 */
	public void randomizeMedia(double p);
}
