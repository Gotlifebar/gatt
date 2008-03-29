package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

/**
 * This class acts like an enumeration holding the constant Boolean operands TRUE and FALSE.
 * @author David
 *
 */
public class ConstBooleanOperand extends BooleanOperand implements Serializable {	
	
	/**
	 * True boolean operand
	 */
	public static ConstBooleanOperand TRUE = new ConstBooleanOperand("true", "True", "verdadero");
	
	/**
	 * False boolean operand
	 */
	public static ConstBooleanOperand FALSE = new ConstBooleanOperand("false", "False", "falso");	
	
	/**
	 * Representation of the operand in java code
	 */
	private String javaString; 
	
	/**
	 * Name of the operand (Mnemotecnic)
	 */
	private String name; 
	
	/**
	 * Readable name of the operand
	 */
	private String readableName;
	
	/** Constructor
	 * @param javaString Representation of the operand in java code
	 * @param name Name of the operand (Mnemotecnic)
	 * @param readableName Readable name of the operand
	 */
	private ConstBooleanOperand(String javaString, String name, String readable){		
		this.javaString = javaString;
		this.name = name;
		this.readableName = readable;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getJavaString()
	 */
	public String getJavaString() {
		return javaString;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getDisplayName()
	 */
	public String getDisplayName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getReadableName()
	 */
	public String getReadableName(){
		return readableName;
	}
}