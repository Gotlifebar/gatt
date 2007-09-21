package org.gatt.optimization.util;

import org.gatt.domain.Group;
import org.gatt.domain.Hour;
import org.gatt.domain.Room;
import org.gatt.domain.Session;
import org.gatt.domain.Subject;
import org.gatt.domain.factories.DomainObjectFactoryFacade;

public class SessionsResetter {
	public void resetSessions(){		
		DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
		//Delete all sessions
		doff.deleteAllSessions();
		//Generate all sessions, based on the groups and the weekly hours of the subject.
		int numberOfGroups = doff.getGroupsCount();
		
		Room r = new Room();//dummy room.
		r.setId(-1);
		Hour h = new Hour();//dummy hour
		h.setId(-1);
		
		for(int i = 0; i < numberOfGroups; i++){
			Group g = doff.getGroup(i);
			Subject sub = g.getSubject();
			int hours = sub.getPracticalHours() + sub.getTheoricalHours();
			int incompleteSessions = hours % 2;
			int fullSessions = hours / 2 - incompleteSessions;
			for(int j = 0 ; j < fullSessions; j++){
				//Create a new full session.
				Session s = new Session();
				s.setGroup(g);
				s.setHour(h);
				s.setRoom(r);
				s.setUsedHours(2);
				doff.insertSession(s);
			}
			if( incompleteSessions == 1 ){
				//Create a new incomplete session.
				Session s = new Session();
				s.setGroup(g);
				s.setHour(h);
				s.setRoom(r);
				s.setUsedHours(1);
				doff.insertSession(s);
			}
		}
	}
}
