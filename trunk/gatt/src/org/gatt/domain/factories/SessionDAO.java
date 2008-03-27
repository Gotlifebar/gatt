package org.gatt.domain.factories;
import org.gatt.domain.Session;

/**
 * @author Chucho
 * Session DAO interface
 */
public interface SessionDAO {
	/**
	 * @param id
	 * Perform the query to find a Session in the database
	 */
	public Session findSession(int id);
	
	/**
	 * return the number of sessions in the database
	 */
	public int countSessions();
	/**
	 * inserts a session to the database
	 * @param session
	 */
	public void insertSession(Session session);
	/**
	 * updates a session in the database
	 * @param session
	 */
	public void updateSession(Session session);
	/**
	 * deletes all session from the database
	 */
	public void deleteAllSessions();
}
