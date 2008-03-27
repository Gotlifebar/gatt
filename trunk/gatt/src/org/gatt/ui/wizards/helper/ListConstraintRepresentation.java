package org.gatt.ui.wizards.helper;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.ui.wizards.ConstraintWizard.ComplementType;

/**
 * @author david
 * representation of the constraint in a list
 */
public class ListConstraintRepresentation implements Serializable {
	
	/**
	 * constraint operator
	 */
	private Operator operator;
	/**
	 * first operand in the constraint
	 */
	private ComparableOperand operand1;
	/**
	 * second operand in the constraint
	 */
	private ComparableOperand operand2;
	/**
	 * type
	 */
	private Class<?> type;
	/**
	 * complement type
	 */
	private ComplementType cType;
	
	
	/**
	 * constructor
	 * @param operator
	 * @param op1
	 * @param op2
	 * @param type
	 * @param cType
	 */
	public ListConstraintRepresentation(Operator operator, ComparableOperand op1, 
										ComparableOperand op2, Class<?> type, ComplementType cType){
		this.operator = operator;
		this.operand1 = op1;
		this.operand2 = op2;
		this.type = type;
		this.cType = cType;
	}

	/**
	 * return operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * return first operand
	 */
	public ComparableOperand getOperand1() {
		return operand1;
	}

	/**
	 * return second operand
	 */
	public ComparableOperand getOperand2() {
		return operand2;
	}

	/**
	 * return type
	 */
	public Class<?> getType() {
		return type;
	}
	
	/**
	 * return complement type
	 */
	public ComplementType getCType() {
		return cType;
	}
	
	/** 
	 * return the representation of the constraint as a string
	 * @see java.lang.Object#toString()
	 */
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
