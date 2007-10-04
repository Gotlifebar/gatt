package org.gatt.ui.wizards.helper;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.ui.wizards.ConstraintWizard.ComplementType;

public class ListConstraintRepresentation implements Serializable {
	
	private Operator operator;
	private ComparableOperand operand1;
	private ComparableOperand operand2;
	private Class<?> type;
	private ComplementType cType;
	
	
	public ListConstraintRepresentation(Operator operator, ComparableOperand op1, 
										ComparableOperand op2, Class<?> type, ComplementType cType){
		this.operator = operator;
		this.operand1 = op1;
		this.operand2 = op2;
		this.type = type;
		this.cType = cType;
	}

	public Operator getOperator() {
		return operator;
	}

	public ComparableOperand getOperand1() {
		return operand1;
	}

	public ComparableOperand getOperand2() {
		return operand2;
	}

	public Class<?> getType() {
		return type;
	}
	
	public ComplementType getCType() {
		return cType;
	}
	
	public String toString(){
		String representation = operand1.getReadableName() + " " + operator.getReadableName() + " " + operand2.getReadableName();
		if(cType != null){
			switch(cType){
				case OR:
					representation = "(OR) " + representation;
					break;
				case AND:
					representation = "(AND) " + representation;
					break;
			}		
		}
			
		return representation;
	}
	
}
