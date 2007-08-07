package org.gatt.constraint;

public class ConstraintValue{
	
	public static final ConstraintValue INFINITY = new ConstraintValue(Double.MAX_VALUE);
	public static final ConstraintValue ZERO = new ConstraintValue(0d);
	public static final ConstraintValue ONE = new ConstraintValue(1d);
	
	private double value;
	
	protected ConstraintValue (double value){
		this.value = value;		
	}	
	public double getValue(){
		return value;
	}
	
}
