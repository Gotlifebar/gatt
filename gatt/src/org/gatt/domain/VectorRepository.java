package org.gatt.domain;
import java.util.Vector;

public class VectorRepository extends Repository{
	private Vector<DomainObject> memory;
	
	public VectorRepository(){
		memory = new Vector<DomainObject>();
	}
	
	public DomainObject getObjectById(int id, Class type){
		//1. Search in the repository for the id-type,
		for(DomainObject dob: memory)
			if( (dob.getId() == id) && (dob.getClass() == type) )
				return dob;
		//if the object is found return it, else return null.		
		return null;
	}
	
	public DomainObject addObject(DomainObject dob){		
		memory.add(dob);
		return dob;
	}
}
