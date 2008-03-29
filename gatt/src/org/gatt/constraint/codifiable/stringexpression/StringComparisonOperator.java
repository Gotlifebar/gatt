package org.gatt.constraint.codifiable.stringexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operator;


public class StringComparisonOperator implements Operator, Serializable{

	/**
	 * Representation of the operator in java code
	 */
	private String javaString; 
	
	/**
	 * Name of the operator (Mnemotecnic)
	 */
	private String displayName; 
	
	/**
	 * Readable name of the operator
	 */
	private String readableName;
	
	
	
	/**
	 * EQUALS operator
	 */
	public static StringComparisonOperator EQUALS = 
		new StringComparisonOperator(".equals", "EQUALS", "IGUAL A " );
	
	/**
	 * NOT_EQUAL operator
	 */
	public static StringComparisonOperator NOT_EQUAL = 
		new StringComparisonOperator(".equals", "NOT_EQUAL", "DIFERENTE DE ");
	
	
	/** Constructor
	 * @param javaString representation of the operator in java code
	 * @param displayName name of the operator (Mnemotecnic)
	 * @param readableName readable name of the operator
	 */
	private StringComparisonOperator(String javaString, String displayName, String readableName ) {				
		this.javaString = javaString;
		this.displayName = displayName;
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
		return displayName;
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
