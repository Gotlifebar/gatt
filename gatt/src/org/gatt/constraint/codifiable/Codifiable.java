package org.gatt.constraint.codifiable;

/**
 * @author David
 * Interface for represent all the objects tan can be transformed in java code
 */

public interface Codifiable {
	
	/**
	 * Represents a simple new Line.
	 */
	public static final String NL = System.getProperty("line.separator");
	
	/** Interface method that must be implemented. It should return the java code representing the object 
	 * @return the java code representing the object
	 */
	public String getJavaString();
}
