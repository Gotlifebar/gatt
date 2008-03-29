package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operator;

/**
 * This class acts like an enumeration holding the possible Boolean operators 
 * handled in the constraint generated code: AND, OR, and NOT operators.
 * @author David
 *
 */
public class BooleanOperator implements Operator, Serializable {
	
	/**
	 * Constant operators
	 */
	public static BooleanOperator AND = new BooleanOperator("&&", "AND", "y"),
								  OR = new BooleanOperator("||", "OR", "o"),
								  NOT = new BooleanOperator("!", "NOT", "no");
	
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
	private BooleanOperator(String javaString, String name, String readableName){
		this.javaString = javaString;
		this.name = name;
		this.readableName = readableName;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.Codifiable#getJavaString()
	 */
	public String getJavaString() {
		return javaString;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.CodifiableDisplayableObject#getDisplayName()
	 */
	public String getDisplayName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.CodifiableDisplayableObject#getReadableName()
	 */
	public String getReadableName(){
		return readableName;
	}
}