package org.gatt.domain;


public abstract class Repository {	
	
	public abstract DomainObject searchInMemory(int id, Class type);/*
		//1. Search in the repository for the id-type,		
		//if the object is found return it, else return null.
	*/
	
	public DomainObject getObjectById(int id, Class type){
		//1. CALL searchInMemory, if it's != null then return the object
		DomainObject dob = searchInMemory(id, type);
		if( dob != null )
			return dob;
		//else
		
		//2. CALL addObject(id, type)
		dob = addObject(id, type);
		//3. return the added object
		return dob;		
	}
	
	protected abstract DomainObject addObject(DomainObject dob);
	
	public DomainObject addObject(int id, Class type){
		//1. Construct a DB Query
		
		//2. Execute the query with the DAO
		
		//3. Construct the DomainObject from the DAO data (maybe the result set? 
		//   or an associative array ?)
		DomainObject dob = null;
		//4. Add the object to the repository in memory
		return addObject(dob);
	}	
	public int getCardinalityOfType(Class type){
		//1. Construct a DB Query for count the number of elements of the type
		
		//2. Execute the query with the DAO
		
		return 0;
	}
}
