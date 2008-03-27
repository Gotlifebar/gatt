package org.gatt.domain;

/**
 * @author Chucho
 * Class Hour
 */
public class Hour extends DomainObject {

	/**
	 * representation of the hour
	 */
	private String representation;
	
	/**
	 * constructor
	 */
	public Hour(){
		super();

	}	
	/**
	 * returns the representation of the hour
	 */
	public String getRepresentation() {
		return representation;
	}
	/**
	 * sets the representation of the hour
	 * @param representation
	 */
	public void setRepresentation(String representation) {
		this.representation = representation;
	}
}
