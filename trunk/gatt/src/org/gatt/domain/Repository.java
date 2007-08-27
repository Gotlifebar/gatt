package org.gatt.domain;


public abstract class Repository {	
	
	public abstract DomainObject getObjectById(int id, Class type);	
	public abstract DomainObject addObject(DomainObject dob);	
}
