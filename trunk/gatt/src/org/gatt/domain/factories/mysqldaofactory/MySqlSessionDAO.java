package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.domain.factories.SessionDAO;
/**
 * @author Chucho
 * MySQL Session DAO
 */
public class MySqlSessionDAO implements SessionDAO {

	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.SessionDAO#countSessions()
	 */
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

	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.SessionDAO#findSession(int)
	 */
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
			
			DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
			s.setGroup(doff.getGroup(r.getInt("group")));
			s.setRoom(doff.getRoom(r.getInt("room")));
			s.setHour(doff.getHour(r.getInt("hour")));
			s.setIsTheorical(r.getInt("isTheorical")==1);
			s.setUsedHours(r.getInt("usedHours"));
			if( s.getIsTheorical() )
				System.out.println("Soy teorico !!!!1");
			//TODO: Llamar a la facade para obtener el grupo, la hora, y el aula como objetos
			
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return s;
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.SessionDAO#insertSession(org.gatt.domain.Session)
	 */
	public void insertSession(Session session){
		Connection c = MySqlDAOFactory.getConnection();
		try{			
			PreparedStatement ps = c.prepareStatement("INSERT INTO sessions(`group`,`hour`,room,isTheorical,usedHours,id) VALUES(?,?,?,?,?,?)");
			ps.setInt(1, session.getGroup().getId());
			ps.setInt(2, session.getHour().getId());
			ps.setInt(3, session.getRoom().getId());
			ps.setInt(4, session.getIsTheorical()?1:0);
			ps.setInt(5, session.getUsedHours());
			ps.setInt(6, session.getId());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.SessionDAO#updateSession(org.gatt.domain.Session)
	 */
	public void updateSession(Session session){
		Connection c = MySqlDAOFactory.getConnection();
		try{			
			PreparedStatement ps = c.prepareStatement("UPDATE sessions SET `group`=?, `hour`=?, room=?, isTheorical=?, usedHours=? WHERE id=?");
			ps.setInt(1, session.getGroup().getId());
			ps.setInt(2, session.getHour().getId());
			ps.setInt(3, session.getRoom().getId());
			ps.setInt(4, session.getIsTheorical()?1:0);
			ps.setInt(5, session.getUsedHours());
			ps.setInt(6, session.getId());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.SessionDAO#deleteAllSessions()
	 */
	public void deleteAllSessions(){
		Connection c= MySqlDAOFactory.getConnection();
		try{
			PreparedStatement ps = c.prepareStatement("DELETE FROM sessions");
			ps.execute();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
