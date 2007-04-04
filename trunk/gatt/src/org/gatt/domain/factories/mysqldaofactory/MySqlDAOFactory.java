package org.gatt.domain.factories.mysqldaofactory;

import org.gatt.domain.factories.*;
import java.sql.*;

public class MySqlDAOFactory extends DAOFactory {
	
	private static Connection conn = null;
	
	// method to create Cloudscape connections
	public static Connection getConnection() {
		if(conn != null)
			return conn;
		try{			
		    DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
//		  	TODO Change this to a loaded configuration property
		    String connUrl = "jdbc:mysql://localhost/gatt";
//		  	TODO Change this to a loaded configuration property
		    String name = "gatt"; 
//		  	TODO Change this to a loaded configuration property
		    String pass = "gatt";
			conn = DriverManager.getConnection(connUrl, name, pass);
		}catch (Exception e){
			e.printStackTrace();						
			return null;
		}
		return conn;
	}
	
	@Override
	public GroupDAO getGroupDAO() {
		return new MySqlGroupDAO();		
	}

	@Override
	public HourDAO getHourDAO() {
		return new MySqlHourDAO();
	}

	@Override
	public MediaTypeDAO getMediaTypeDAO() {
		return new MySqlMediaTypeDAO();		
	}

	@Override
	public RoomDAO getRoomDAO() {		
		return new MySqlRoomDAO();
	}

	@Override
	public SessionDAO getSessionDAO() {
		return new MySqlSessionDAO();		
	}

	@Override
	public SubjectDAO getSubjectDAO() {
		return new MySqlSubjectDAO();
	}

	@Override
	public TeacherDAO getTeacherDAO() {		
		return new MySqlTeacherDAO();
	}
}
