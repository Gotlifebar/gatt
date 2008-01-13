package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

public class BooleanOperand extends ComparableOperand implements Serializable {
	public BooleanOperand (String javaString, String name, String readableName){
		super(javaString, name, readableName);
	}
	public BooleanOperand(){
		
	}
	public Class getValueType() {		
		return boolean.class;		
	}	
}
