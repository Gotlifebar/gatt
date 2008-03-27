package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.gatt.domain.Teacher;
import org.gatt.domain.factories.TeacherDAO;
/**
 * @author Chucho
 * MySQL teacher DAO
 */
public class MySqlTeacherDAO implements TeacherDAO {

	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.TeacherDAO#findTeacher(int)
	 */
	public Teacher findTeacher(int registerNumber) {
		Connection c = MySqlDAOFactory.getConnection();
		Teacher t = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM teachers WHERE registerNumber = ?");
			ps.setInt(1, registerNumber);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() )
				return null;
			//Was found... create
			t = new Teacher();
			t.setLastName(r.getString("lastName"));
			t.setName(r.getString("name"));
			t.setRegisterNumber(r.getInt("registerNumber"));
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see org.gatt.domain.factories.TeacherDAO#countTeachers()
	 */
	public int countTeachers() {
		Connection c = MySqlDAOFactory.getConnection();		
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM teachers");			
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
