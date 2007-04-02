package org.gatt.domain;
import java.util.*;

public class VectorRepository extends Repository{
	private Vector<DomainObject> memory;
	
	public DomainObject searchInMemory(int id, Class type){
		//1. Search in the repository for the id-type,
		for(DomainObject dob: memory)
			if( (dob.getId() == id) && (dob.getClass() == type) )
				return dob;
		//if the object is found return it, else return null.		
		return null;
	}
	
	protected DomainObject addObject(DomainObject dob){		
		memory.add(dob);
		return dob;
	}
}
