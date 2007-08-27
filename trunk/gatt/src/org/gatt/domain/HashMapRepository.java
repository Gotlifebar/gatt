package org.gatt.domain;

import java.util.HashMap;

public class HashMapRepository extends Repository {
	private HashMap<String, DomainObject> hash;
	
	public HashMapRepository(){
		hash = new HashMap<String, DomainObject>();
	}
	
	@Override
	protected DomainObject addObject(DomainObject dob) {
		String key = dob.getClass().getSimpleName() + dob.getId();
		hash.put(key, dob);
		return dob;
	}

	@Override
	public DomainObject searchInMemory(int id, Class type) {		
		return hash.get(type.getSimpleName() + id);
	}

}
