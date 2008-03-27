package org.gatt.domain;
import java.util.Vector;

/**
 * @author david
 * A repository stored in a Vector object
 */
public class VectorRepository extends Repository{
	/**
	 * Vector object 
	 */
	private Vector<DomainObject> memory;
	
	/**
	 * constructor
	 */
	public VectorRepository(){
		memory = new Vector<DomainObject>();
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.domain.Repository#getObjectById(int, java.lang.Class)
	 */
	public DomainObject getObjectById(int id, Class type){
		//1. Search in the repository for the id-type,
		for(DomainObject dob: memory)
			if( (dob.getId() == id) && (dob.getClass() == type) )
				return dob;
		//if the object is found return it, else return null.		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.domain.Repository#addObject(org.gatt.domain.DomainObject)
	 */
	public DomainObject addObject(DomainObject dob){		
		memory.add(dob);
		return dob;
	}
}
