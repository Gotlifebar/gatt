package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.gatt.domain.Group;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.domain.factories.GroupDAO;

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
			if( !r.next() ){
				System.out.println("No lo encontró al grupo: " + id);
				return null;
			}
			
			DomainObjectFactoryFacade facade = DomainObjectFactoryFacade.getInstance(); 
			
			//Was found... create
			g = new Group();
			g.setNumStudents(r.getInt("numStudents"));
			g.setNumber(r.getInt("number"));
			g.setId(r.getInt("id"));
			g.setSubject(facade.getSubjectByCode(r.getInt("subject")));
			//g.setTeacher(facade.getTeacher(r.getInt("teacher")));
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
