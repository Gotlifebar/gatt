package org.gatt.domain.factories.mysqldaofactory;

import org.gatt.domain.Subject;
import org.gatt.domain.factories.SubjectDAO;
import java.sql.*;
public class MySqlSubjectDAO implements SubjectDAO {
	
	public int countSubjects() {
		Connection c = MySqlDAOFactory.getConnection();		
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM subjects");			
			ResultSet r = ps.executeQuery();			
			if( !r.next() )
				return -1;
			return r.getInt("total");			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public Subject findSubject(int id) {
		Connection c = MySqlDAOFactory.getConnection();
		Subject s = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM subjects WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() ){
				System.out.println("No encontró SUBJECT: " + id);
				return null;
			}
			//Was found... create
			s = new Subject();
			s.setCode(r.getInt("code"));
			s.setName(r.getString("name"));
			s.setLetterCode(r.getString("letterCode"));
			s.setTheoricalHours(r.getInt("theoricalHours"));
			s.setPracticalHours(r.getInt("practicalHours"));
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return s;
	}
	
	public Subject findSubjectByCode(int code){
		Connection c = MySqlDAOFactory.getConnection();
		Subject s = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM subjects WHERE code = ?");
			ps.setInt(1, code);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() ){
				System.out.println("No encontró SUBJECT (code): " + code);
				return null;
			}
			//Was found... create
			s = new Subject();
			s.setId(r.getInt("id"));
			s.setCode(r.getInt("code"));
			s.setName(r.getString("name"));
			s.setLetterCode(r.getString("letterCode"));
			s.setTheoricalHours(r.getInt("theoricalHours"));
			s.setPracticalHours(r.getInt("practicalHours"));
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return s;
	}
}
