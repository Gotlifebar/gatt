package org.gatt.domain;

/**
 * @author Chucho
 * Teacher class
 */
public class Teacher extends DomainObject {
	
	/**
	 * teacher's id
	 */
	private int registerNumber;
	/**
	 * teacher's name
	 */
	private String name;
	/**
	 * teacher's last name
	 */
	private String lastName;
	
	/**
	 * constructor
	 */
	public Teacher(){
		super();
	}
	/**
	 * return teacher's id
	 */
	public int getRegisterNumber() {
		return registerNumber;
	}
	/**
	 * sets techer's id
	 * @param registerNumber
	 */
	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	/**
	 * return teacher's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets teacher's name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * returns teacher's last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * sets teacher's last name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
