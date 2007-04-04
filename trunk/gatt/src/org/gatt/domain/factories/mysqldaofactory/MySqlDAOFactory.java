package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.gatt.domain.factories.DAOFactory;
import org.gatt.domain.factories.GroupDAO;
import org.gatt.domain.factories.HourDAO;
import org.gatt.domain.factories.MediaTypeDAO;
import org.gatt.domain.factories.RoomDAO;
import org.gatt.domain.factories.SessionDAO;
import org.gatt.domain.factories.SubjectDAO;
import org.gatt.domain.factories.TeacherDAO;
import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;


public class MySqlDAOFactory extends DAOFactory {
	
	private static Connection conn = null;
	
	// method to create Cloudscape connections
	public static Connection getConnection() {
		if(conn != null)
			return conn;
		
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);
		
		try{			
		    DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		    String connUrl = config.getValue("MySqlConnectionInfo", "URLConnectionString");
		    String name = config.getValue("MySqlConnectionInfo", "UserConnection"); 
		    String pass = config.getValue("MySqlConnectionInfo", "PasswordConnection");
			conn = DriverManager.getConnection(connUrl, name, pass);
		}catch (SQLException sqlEx){
			sqlEx.printStackTrace();						
			return null;
		}catch(JFigException jFigEx){
			jFigEx.printStackTrace();						
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
