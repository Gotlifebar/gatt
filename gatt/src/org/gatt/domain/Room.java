package org.gatt.domain;

/**
 * @author Chucho
 * Room class
 */
public class Room extends DomainObject {
	
	/**
	 * number of the room
	 */
	private String number;
	/**
	 * capacity of the room
	 */
	private int capacity;
	/**
	 * type of the room
	 */
	private String type;	

	/**
	 * constructor
	 */
	public Room(){
		super();
	}
	
	/**
	 * return the number of the room
	 */
	public String getNumber(){
		return this.number;
	}
	/**
	 * returns the capacity of the room
	 */
	public int getCapacity(){
		return this.capacity;
	}
	/**
	 * returns the type of the room
	 */
	public String getType(){
		return this.type;
	}	

	/**
	 * sets the number of the room
	 * @param number
	 */
	public void setNumber(String number){
		this.number = number;
	}
	/**
	 * sets the capacity of the room
	 * @param capacity
	 */
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	/**
	 * sets the type of the room
	 * @param type
	 */
	public void setType(String type){
		this.type = type;
	}
}
