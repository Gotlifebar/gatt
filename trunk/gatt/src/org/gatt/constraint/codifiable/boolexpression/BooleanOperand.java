package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

/**
 * This abstract class is a specialization of the interface operand, 
 * and is related to the Boolean operands and must be extended by all the possible Boolean operands.
 * @author David
 *
 */
public class BooleanOperand extends ComparableOperand implements Serializable {
	/** Constructor
	 * @param javaString representation of the operand in java code
	 * @param name name of the operand
	 * @param readableName readable name of the operand
	 */
	public BooleanOperand (String javaString, String name, String readableName){
		super(javaString, name, readableName);
	}
	
	/**
	 * Void constructor
	 */
	public BooleanOperand(){
		
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.Operand#getValueType()
	 */
	public Class getValueType() {		
		return boolean.class;		
	}	
}
