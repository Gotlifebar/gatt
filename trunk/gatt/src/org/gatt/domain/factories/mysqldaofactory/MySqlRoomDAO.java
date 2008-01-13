package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.gatt.domain.Room;
import org.gatt.domain.factories.RoomDAO;
public class MySqlRoomDAO implements RoomDAO {

	public int countRooms() {
		Connection c = MySqlDAOFactory.getConnection();
		try{
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM Rooms");			
			ResultSet r = ps.executeQuery();			
			if( !r.next() )
				return -1;
			return r.getInt("total");			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	public Collection<Room> findAll(){
		Connection c = MySqlDAOFactory.getConnection();
		Vector<Room> rooms = new Vector<Room>();
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Rooms");
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			while( r.next() ){
				Room room = new Room();
				room.setNumber(r.getString("number"));				
				room.setCapacity(r.getInt("capacity"));				
				room.setType(r.getString("type"));				
				room.setId(r.getInt("id"));				
				rooms.addElement(room);
			}
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return rooms;
	}	

	public Room findRoom(int id) {
		Connection c = MySqlDAOFactory.getConnection();
		Room room = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Rooms WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() )
				return null;
			//Was found... create
			room = new Room();
			
			room.setNumber(r.getString("number"));				
			room.setCapacity(r.getInt("capacity"));				
			room.setType(r.getString("type"));				
			room.setId(r.getInt("id"));				
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return room;
	}

}
