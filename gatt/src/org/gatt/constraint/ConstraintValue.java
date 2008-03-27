package org.gatt.constraint;

/**
 * @author David
 * Class that represents a possible value of a constraint
 */
/**
 * @author Chucho
 *
 */
public class ConstraintValue{
	
	/**
	 * value infinity 
	 */
	public static final ConstraintValue INFINITY = new ConstraintValue(Double.MAX_VALUE);
	/**
	 * value zero (0)
	 */
	public static final ConstraintValue ZERO = new ConstraintValue(0d);
	
	/**
	 * Value one (1)
	 */
	public static final ConstraintValue ONE = new ConstraintValue(1d);
	
	/**
	 * Stores the constraint value 
	 */
	private double value;
	
	/**
	 * Constructor
	 * @param value
	 */
	protected ConstraintValue (double value){
		this.value = value;		
	}	
	
	/**
	 * return the constraint value
	 */
	public double getValue(){
		return value;
	}
	
}
