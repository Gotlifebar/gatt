package org.gatt.optimization.util;

import org.gatt.domain.Subject;
import org.gatt.domain.factories.DomainObjectFactoryFacade;

public class SessionsResetter {
	public void resetSessions(){
		DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
		//Get all sessions.
		//int numberOfRooms = dofFacade.getRoomsCount();
		//int numberOfHours = dofFacade.getHoursCount();
		int numberOfGroups = doff.getGroupsCount();
		for(int i = 0; i < numberOfGroups; i++){
			Subject sub = doff.getGroup(i).getSubject();
			int hours = sub.getPracticalHours() + sub.getTheoricalHours();
			int incompleteSessions = hours % 2;
			int fullSessions = hours / 2 - incompleteSessions;
			//for(int j = 0 ; j < )
		}
		//sessions[i].setGroup(doff.getGroup((Integer)gene[i].getAllele()));	
	}
}
