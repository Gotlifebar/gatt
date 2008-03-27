package org.gatt.domain;

/**
 * @author david
 * base class for all domain objects
 */
public abstract class DomainObject implements java.io.Serializable{
	
	/**
	 * ID of the domain object 
	 */
	private int id;	
	
	/**
	 * Empty Constructor 
	 */
	public DomainObject(){
		
	}
	
	/**
	 * Constructor
	 * @param id
	 */
	public DomainObject(int id){
		this.id = id;
	}
	
	/**
	 *  Return the id of the domain object	 
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * sets the id of the domain object
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
}
