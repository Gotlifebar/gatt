package org.gatt.domain.factories;

/**
 * @author Chucho
 * Factory that creates Data Access Objects
 */
public abstract class DAOFactory {
	
	/**
	 * return a Group Data Access Object
	 */
	public abstract GroupDAO getGroupDAO();
	/**
	 * return a Hour Data Access Object
	 */
	public abstract HourDAO getHourDAO();
	/**
	 * return a MediaType Data Access Object
	 */
	public abstract MediaTypeDAO getMediaTypeDAO();
	/**
	 * return a Room Data Access Object
	 */
	public abstract RoomDAO getRoomDAO();
	/**
	 * return a Session Data Access Object
	 */
	public abstract SessionDAO getSessionDAO();
	/**
	 * return a Subject Data Access Object
	 */
	public abstract SubjectDAO getSubjectDAO();
	/**
	 * return a Teacher Data Access Object
	 */
	public abstract TeacherDAO getTeacherDAO();
	/**
	 * return an InitialTT Data Access Object
	 */
	public abstract InitialTTDAO getInitialTTDAO();
	
	/**
	 * 
	 * @param daoFactory
	 * return a specific DAO factory.
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static DAOFactory getDAOFactory(String daoFactory)
		throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return (DAOFactory) ClassLoader.getSystemClassLoader().loadClass(daoFactory).newInstance();
	}
}
