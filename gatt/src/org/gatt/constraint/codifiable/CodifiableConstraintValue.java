package org.gatt.constraint.codifiable;

import org.gatt.constraint.ConstraintValue;

public class CodifiableConstraintValue extends ConstraintValue implements Codifiable {
	public static final CodifiableConstraintValue INFINITY = new CodifiableConstraintValue(Double.MAX_VALUE, "ConstraintValue.INFINITY");
	public static final CodifiableConstraintValue ZERO = new CodifiableConstraintValue(0d, "ConstraintValue.ZERO");
	public static final CodifiableConstraintValue ONE = new CodifiableConstraintValue(1d, "ConstraintValue.ONE");
	
	private String javaRepresentation;
	
	private CodifiableConstraintValue(double val, String rep){
		super(val);
		javaRepresentation = rep;
	}
	
	public String getJavaString(){
		return javaRepresentation;
	}
}
