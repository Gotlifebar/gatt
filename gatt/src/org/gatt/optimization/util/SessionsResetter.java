package org.gatt.optimization.util;

import org.gatt.domain.factories.DomainObjectFactoryFacade;

public class SessionsResetter {
	public void resetSessions(){
		DomainObjectFactoryFacade dofFacade = DomainObjectFactoryFacade.getInstance();
		//Get all sessions.
		int numberOfRooms = dofFacade.getRoomsCount();
		int numberOfHours = dofFacade.getHoursCount();
		int numberOfGroups = dofFacade.getGroupsCount();
	}
}
