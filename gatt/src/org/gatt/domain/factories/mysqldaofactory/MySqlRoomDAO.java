package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.gatt.domain.Room;
import org.gatt.domain.factories.RoomDAO;
/**
 * @author Chucho
 * MySQL Room DAO
 */
public class MySqlRoomDAO implements RoomDAO {
/*	class Constraint_1205788055093 implements Constraint{
		public Constraint_1205788055093(){}	public double getSignificance(){
			return 0.79;
		}	
		public ConstraintValue evaluate(Session[] session){
			for(int i = 0; i < session.length; i++){
				if(session[i] == null) continue;			
				if( !session[i].getGroup().applyConstraint("1205788055093")) continue;
				if((session[i].getIsTheorical() == true) && (!(session[i].getRoom().getType().equals( "Y") )))
					return ConstraintValue.ONE;
			}
			return ConstraintValue.ZERO;
		}
	}*/
	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.RoomDAO#countRooms()
	 */
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
	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.RoomDAO#findAll()
	 */
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

	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.RoomDAO#findRoom(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.RoomDAO#randomizeMedia(double)
	 */
	public void randomizeMedia(double p){
		Connection c = MySqlDAOFactory.getConnection();
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Rooms", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			String newType = "Y";
			while( r.next() ){
				if( Math.random() < p )
					r.updateString("type", "MEDIOS");
				//	newType = "Y"; 
				else
					r.updateString("type", "");
				//	newType = "N";
				r.updateRow();
				/*PreparedStatement up = c.prepareStatement("UPDATE Rooms SET type = ? WHERE id = ?");
				up.setString(1, newType);
				up.setInt(2, r.getInt("id"));
				up.executeUpdate();*/
				/*Room room = new Room();
				room.setNumber(r.getString("number"));
				room.setCapacity(r.getInt("capacity"));
				room.setType(r.getString("type"));
				room.setId(r.getInt("id"));
				rooms.addElement(room);*/
			}
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Randomized the media to " + p + " percent");
	}
}
