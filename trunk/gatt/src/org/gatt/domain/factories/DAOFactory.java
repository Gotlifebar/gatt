package org.gatt.domain.factories;
import org.gatt.domain.*;

public abstract class DAOFactory {
	public abstract GroupDAO getGroupDAO();
	public abstract HourDAO getHourDAO();
	public abstract MediaTypeDAO getMediaTypeDAO();
	public abstract RoomDAO getRoomDAO();
	public abstract SessionDAO getSessionDAO();
	public abstract SubjectDAO getSubjectDAO();
	public abstract TeacherDAO getTeacherDAO();
	
	public static DAOFactory getDAOFactory(String daoFactory)
		throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return (DAOFactory) ClassLoader.getSystemClassLoader().loadClass(daoFactory).newInstance();
	}
}
