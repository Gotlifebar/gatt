package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.gatt.domain.Hour;
import org.gatt.domain.factories.HourDAO;

public class MySqlHourDAO implements HourDAO {

	public int countHours() {
		Connection c = MySqlDAOFactory.getConnection();		
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM hours");			
			ResultSet r = ps.executeQuery();			
			if( !r.next() )
				return -1;
			return r.getInt("total");			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public Hour findHour(int id) {
		Connection c = MySqlDAOFactory.getConnection();
		Hour h = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM hours WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() ){
				System.out.println("No lo encontro: " + id);
				return null;
			}
			//Was found... create
			h = new Hour();
			h.setId(r.getInt("id"));
			//h.setDay(r.getInt("day"));
			//h.setTime(r.getInt("time"));
			h.setRepresentation(r.getString("representation"));
			
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return h;
	}
	
	public Collection<Hour> findAll(){
		Connection c = MySqlDAOFactory.getConnection();
		Vector<Hour> hours = new Vector<Hour>();
		
		try{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM hours");
			ResultSet r = ps.executeQuery();
			
			while(r.next()){
				Hour h = new Hour();
				h.setId(r.getInt("id"));
				//h.setDay(r.getInt("day"));
				//h.setTime(r.getInt("time"));
				h.setRepresentation(r.getString("representation"));
				hours.addElement(h);
			}
			
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return hours;
	}

}
