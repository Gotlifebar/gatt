package org.gatt.domain.factories.mysqldaofactory;

import org.gatt.domain.Subject;
import org.gatt.domain.factories.SubjectDAO;
import java.sql.*;
public class MySqlSubjectDAO implements SubjectDAO {
	public int countSubjects() {
		Connection c = MySqlDAOFactory.getConnection();		
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM tbl_Subject");			
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
			PreparedStatement ps = c.prepareStatement("SELECT * FROM tbl_Subject WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() )
				return null;
			//Was found... create
			s = new Subject();
			s.setCode(r.getString("code"));
			s.setName(r.getString("name"));
			s.setProgram(r.getInt("program"));
			s.setSemester(r.getInt("semester"));
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return s;
	}
}