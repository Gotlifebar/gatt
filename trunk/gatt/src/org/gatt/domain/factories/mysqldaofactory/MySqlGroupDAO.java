package org.gatt.domain.factories.mysqldaofactory;

import org.gatt.domain.Group;
import org.gatt.domain.Subject;
import org.gatt.domain.factories.GroupDAO;
import java.sql.*;

public class MySqlGroupDAO implements GroupDAO {

	public Group findGroup(int id) {	
		Connection c = MySqlDAOFactory.getConnection();
		Group g = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM groups WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() )
				return null;
			//Was found... create
			g = new Group();
			g.setMaxCapacity(r.getInt("maxCapacity"));
			g.setNumber(r.getInt("number"));
			// what about subject and teacher ?
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return g;
	}

	public int countGroups() {
		Connection c = MySqlDAOFactory.getConnection();		
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM groups");			
			ResultSet r = ps.executeQuery();			
			if( !r.next() )
				return -1;
			return r.getInt("total");			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
}
