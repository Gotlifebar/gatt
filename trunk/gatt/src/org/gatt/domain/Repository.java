package org.gatt.domain;


/**
 * @author david
 * abstract class that represents a repository
 */
public abstract class Repository {	
	
	/**
	 * @param id
	 * @param type
	 * returns an object from the repository by its id
	 */
	public abstract DomainObject getObjectById(int id, Class type);
	
	/**
	 * @param dob
	 * adds an object to the repository
	 */
	public abstract DomainObject addObject(DomainObject dob);	
}
