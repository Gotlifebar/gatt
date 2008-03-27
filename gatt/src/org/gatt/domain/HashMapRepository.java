package org.gatt.domain;

import java.util.HashMap;

/**
 * @author david
 * domain objects repository
 */
public class HashMapRepository extends Repository {
	
	/**
	 * Hashmap to store objects 
	 */
	private HashMap<String, DomainObject> hash;
	
	/**
	 * constructor
	 */
	public HashMapRepository(){
		hash = new HashMap<String, DomainObject>();
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.domain.Repository#addObject(org.gatt.domain.DomainObject)
	 */
	@Override
	public DomainObject addObject(DomainObject dob) {
		String key = dob.getClass().getSimpleName() + dob.getId();
		hash.put(key, dob);
		return dob;
	}

	/* (non-Javadoc)
	 * @see org.gatt.domain.Repository#getObjectById(int, java.lang.Class)
	 */
	@Override
	public DomainObject getObjectById(int id, Class type) {		
		return hash.get(type.getSimpleName() + id);
	}

}
