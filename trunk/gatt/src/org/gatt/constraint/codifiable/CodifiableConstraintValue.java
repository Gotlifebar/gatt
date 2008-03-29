package org.gatt.constraint.codifiable;

import org.gatt.constraint.ConstraintValue;

/**
 * Class representing the value that can be returned from a constraint that can be generated in java code.
 * It works as an enumeration.
 * @author David
 *
 */
public class CodifiableConstraintValue extends ConstraintValue implements Codifiable {
	
	/**
	 * Constant that represents the INFINITY value.
	 */
	public static final CodifiableConstraintValue INFINITY = new CodifiableConstraintValue(Double.MAX_VALUE, "ConstraintValue.INFINITY");
	
	/**
	 * Constant that represents the ZERO value.
	 */
	public static final CodifiableConstraintValue ZERO = new CodifiableConstraintValue(0d, "ConstraintValue.ZERO");
	
	/**
	 * Constant that represents the ONE value.
	 */
	public static final CodifiableConstraintValue ONE = new CodifiableConstraintValue(1d, "ConstraintValue.ONE");
	
	/**
	 * The representation of the object in java code.
	 */
	private String javaRepresentation;
	
	/** Constructor for each constant value.
	 * @param val the numeric value of the constant
	 * @param rep the string representation in java code
	 */
	private CodifiableConstraintValue(double val, String rep){
		super(val);
		javaRepresentation = rep;
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.Codifiable#getJavaString()
	 */
	public String getJavaString(){
		return javaRepresentation;
	}
}
