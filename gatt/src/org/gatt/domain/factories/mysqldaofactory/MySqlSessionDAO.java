package org.gatt.domain.factories.mysqldaofactory;

import org.gatt.domain.Session;
import org.gatt.domain.Teacher;
import org.gatt.domain.factories.SessionDAO;
import java.sql.*;
public class MySqlSessionDAO implements SessionDAO {

	public int countSessions() {
		Connection c = MySqlDAOFactory.getConnection();		
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM sessions");			
			ResultSet r = ps.executeQuery();			
			if( !r.next() )
				return -1;
			return r.getInt("total");			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public Session findSession(int id) {
		Connection c = MySqlDAOFactory.getConnection();
		Session s = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM sessions WHERE id = ?");
			ps.setInt(1, id);
			
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() )
				return null;
			//Was found... create
			s = new Session();
			s.setId(r.getInt("id"));
			//TODO: Llamar a la facade para obtener el grupo, la hora, y el aula como objetos
			
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return s;
	}
	
	public void insertSession(Session session){
		Connection c = MySqlDAOFactory.getConnection();
		try{			
			PreparedStatement ps = c.prepareStatement("INSERT INTO sessions(group,hour,room) VALUES(?,?,?)");
			ps.setInt(1, session.getGroup().getId());
			ps.setInt(2, session.getHour().getId());
			ps.setInt(3, session.getRoom().getId());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateSession(Session session){
		Connection c = MySqlDAOFactory.getConnection();
		try{			
			PreparedStatement ps = c.prepareStatement("UPDATE sessions SET group=?, hour=?, room=? WHERE id=?");
			ps.setInt(1, session.getGroup().getId());
			ps.setInt(2, session.getHour().getId());
			ps.setInt(3, session.getRoom().getId());
			ps.setInt(4, session.getId());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
