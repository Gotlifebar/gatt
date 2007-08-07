package org.gatt.constraint.codifiable.boolexpression;

import org.gatt.constraint.codifiable.Operand;

public abstract class BooleanOperand implements Operand {
	public Class getValueType() {		
		return boolean.class;
	}
	
}
