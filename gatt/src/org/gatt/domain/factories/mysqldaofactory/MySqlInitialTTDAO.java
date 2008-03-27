package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.gatt.domain.InitialTT;
import org.gatt.domain.factories.InitialTTDAO;

/**
 * @author Chucho
 * MySQL InitialTT DAO
 */
public class MySqlInitialTTDAO implements InitialTTDAO{

	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.InitialTTDAO#countInitial_Timetabling()
	 */
	public int countInitial_Timetabling() {
		Connection c = MySqlDAOFactory.getConnection();
		try{
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM Initial_Timetabling");			
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
	 * @see org.gatt.domain.factories.InitialTTDAO#findAll()
	 */
	public Collection<InitialTT> findAll(){
		Connection c = MySqlDAOFactory.getConnection();
		Vector<InitialTT> initial_timetabling = new Vector<InitialTT>();
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Initial_Timetabling");
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			while( r.next() ){
				InitialTT initialtt = new InitialTT();
				initialtt.setGroup(r.getInt("group_id"));				
				initialtt.setRoom(r.getInt("room_id"));				
				initialtt.setHour(r.getInt("hour_id"));				
				initial_timetabling.addElement(initialtt);
			}
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return initial_timetabling;
	}	

	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.InitialTTDAO#findInitialTT(int)
	 */
	public InitialTT findInitialTT(int id) {	
		return null;
	}
}