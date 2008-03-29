package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operator;


/**
 * This class acts like an enumeration holding the possible Operators of a comparison. 
 * The operators supported are: EQUAL, LESS_THAN, LESS_EQUAL_THAN, MORE_THAN, MORE_EQUAL_THAN, NOT_EQUAL.
 * @author David
 *
 */
public class DefaultComparisonOperator implements Operator, Serializable {
	
	/**
	 * Supported operators
	 */
	public static DefaultComparisonOperator EQUAL = new DefaultComparisonOperator("==", "EQUAL", "es igual a"),
									 LESS_THAN = new DefaultComparisonOperator("<", "LESS_THAN", "es menor que"),
									 LESS_EQUAL_THAN = new DefaultComparisonOperator("<=", "LESS_EQUAL_THAN", "es menor o igual que"),
									 MORE_THAN = new DefaultComparisonOperator(">", "MORE_THAN", "es mayor que"),
									 MORE_EQUAL_THAN = new DefaultComparisonOperator(">=", "MORE_EQUAL_THAN", "es mayor o igual que"),
									 NOT_EQUAL = new DefaultComparisonOperator("!=", "NOT_EQUAL", "es diferente a");
	
	/**
	 * Representation of the operator in java code
	 */
	private String javaString; 
	
	/**
	 * Name of the operator (Mnemotecnic)
	 */
	private String name; 
	
	/**
	 * Readable name of the operator
	 */
	private String readableName;
	
	/** Constructor
	 * @param javaString representation of the operator in java code
	 * @param name name of the operator (Mnemotecnic)
	 * @param readableName readable name of the operator
	 */
	protected DefaultComparisonOperator(String javaString, String name, String readableName){
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return readableName;
	}
}