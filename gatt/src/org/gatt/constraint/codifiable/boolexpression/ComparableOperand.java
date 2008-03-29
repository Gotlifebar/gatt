package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operand;


/** 
 * This abstract class implements the Operand interface and represents an operand in a comparison operation and 
 * java code can be generated from it.
 * @author David
 *
 */
public abstract class ComparableOperand implements Operand, Serializable {
	
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
	public ComparableOperand(String javaString, String name, String readableName){
		this.javaString = javaString;
		this.name = name;
		this.readableName = readableName;
	}
	/**
	 * Void constructor
	 */
	public ComparableOperand(){
		
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
	/** Sets the java string representation in code
	 * @param javaString javas string representation in code
	 */
	public void setJavaString(String javaString) {
		this.javaString = javaString;
	}
	/** Sets the name of the operand
	 * @param name name of the operand
	 */
	public void setDisplayName(String name) {
		this.name = name;
	}
	
	/** Sets the readable name of the operand
	 * @param readableName readable name of the operand
	 */
	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}
}
