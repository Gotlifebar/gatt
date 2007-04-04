package org.gatt.domain;

public abstract class DomainObject implements java.io.Serializable{
	private int id;	
	public DomainObject(){
		
	}
	public DomainObject(int id){
		this.id = id;
	}	
	/** Return the id in the database	 
	 * @return the id in the database
	 */
	public int getId(){
		return id;
	}	
}
