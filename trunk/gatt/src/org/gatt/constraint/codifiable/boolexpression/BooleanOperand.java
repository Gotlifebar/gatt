package org.gatt.constraint.codifiable.boolexpression;

import org.gatt.constraint.codifiable.Operand;

public class BooleanOperand extends ComparableOperand {
	public BooleanOperand (String javaString, String name, String readableName){
		super(javaString, name, readableName);
	}
	public BooleanOperand(){
		
	}
	public Class getValueType() {		
		return boolean.class;		
	}	
}
