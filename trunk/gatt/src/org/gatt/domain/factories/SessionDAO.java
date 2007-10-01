package org.gatt.domain.factories;
import org.gatt.domain.*;

public interface SessionDAO {
	public Session findSession(int id);
	public int countSessions();
	public void insertSession(Session session);
	public void updateSession(Session session);
	public void deleteAllSessions();
}
