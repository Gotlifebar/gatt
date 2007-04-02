package org.gatt.domain;

public class Repository {
	
	public DomainObject searchInMemory(int id, Class type){
		//1. Search in the repository for the id-type, 
		//if the object is found return it, else return null.		
		return null;
	}
	
	public DomainObject getObjectById(int id, Class type){
		//1. CALL searchInMemory, if it's != null then return the object
		
		//else
		
		//2. CALL addObject(id, type)
		
		//3. return the added object
		
		return null;
	}
	public void addObject(int id, Class type){
		//1. Construct a DB Query
		
		//2. Execute the query with the DAO
		
		//3. Add the object to the repository in memory
	}
	public int getCardinalityOfType(Class type){
		//1. Construct a DB Query for count the number of elements of the type
		
		//2. Execute the query with the DAO		
		
		return 0;
	}
}
