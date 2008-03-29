package org.gatt.constraint.codifiable.stringexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;

/**
 * This class represents a string operand which is an operator in a string comparison. 
 * @author David
 *
 */
public class StringComparableOperand extends ComparableOperand implements Serializable {
	/** Constructor
	 * @param javaString Representation of the operand in java code
	 * @param name Name of the operand (Mnemotecnic)
	 * @param readableName Readable name of the operand
	 */
	public StringComparableOperand(String javaString, String name, String readableName){
		super(javaString, name, readableName);
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.Operand#getValueType()
	 */
	public Class getValueType() {		
		return String.class;
	}

}
