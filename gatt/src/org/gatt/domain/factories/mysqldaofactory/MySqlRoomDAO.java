package org.gatt.domain.factories.mysqldaofactory;

import org.gatt.domain.Hour;
import org.gatt.domain.Room;
import org.gatt.domain.factories.RoomDAO;
import java.sql.*;
public class MySqlRoomDAO implements RoomDAO {

	public int countRooms() {
		Connection c = MySqlDAOFactory.getConnection();		
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM rooms");			
			ResultSet r = ps.executeQuery();			
			if( !r.next() )
				return -1;
			return r.getInt("total");			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public Room findRoom(int id) {
		Connection c = MySqlDAOFactory.getConnection();
		Room room = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM rooms WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() )
				return null;
			//Was found... create
			room = new Room();
			room.setId(r.getInt("id"));
			room.setCurrentChairs(r.getInt("currentChairs"));
			room.setMaxChairs(r.getInt("maxChairs"));
			room.setSpace(r.getString("space"));
			room.setType(r.getString("type"));
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return room;
	}

}
