package org.gatt.constraint.codifiable.stringexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;

public class StringComparableOperand extends ComparableOperand implements Serializable {
	public StringComparableOperand(String javaString, String name, String readableName){
		super(javaString, name, readableName);
	}
	public Class getValueType() {		
		return String.class;
	}

}
