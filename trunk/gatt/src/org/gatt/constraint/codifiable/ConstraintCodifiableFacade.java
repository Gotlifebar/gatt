package org.gatt.constraint.codifiable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparison;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.constraint.codifiable.stringexpression.StringComparableOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparison;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;

public class ConstraintCodifiableFacade {
	
	public static final int CONDITIONAL_CONSTRAINT = 1,
							SIMPLE_CONSTRAINT = 2;
							
	
	public ConstraintCodifiableFacade(){
		
	}
	public DefaultComparison createDefaultComparison(DefaultComparisonOperator op, ComparableOperand op1, ComparableOperand op2){
		return new DefaultComparison(op, op1, op2);
	}
	public StringComparison createStringComparison(StringComparisonOperator op, StringComparableOperand op1, StringComparableOperand op2){
		return new StringComparison(op, op1, op2);
	}
	public ComparableOperand createOperand(String javaString, String name, String readableName, Class type){
		if( type == String.class)
			return new StringComparableOperand(javaString, name, readableName);
		return new BooleanOperand(javaString, name, readableName);		
	}
	public BooleanOperand addANDComparison(BooleanOperand operand, BooleanOperand comparison){
		return (new CompositeBooleanExpression(BooleanOperator.AND, operand, comparison));		
	}
	public BooleanOperand addORComparison(BooleanOperand operand, BooleanOperand comparison){
		return (new CompositeBooleanExpression(BooleanOperator.OR, operand, comparison));		
	}
	public CodifiableDisplayableObject createConstraint(int type, CodifiableConstraintValue penalty, BooleanOperand ... params){
		CodifiableDisplayableObject constraint = null;
		switch(type){
			case SIMPLE_CONSTRAINT:
				constraint = new SimpleConstraint(params[0], penalty);
				break;
			case CONDITIONAL_CONSTRAINT:
				constraint = new ConditionalConstraint(params[0], params[1], penalty);
				break;
		}
		return constraint;
	}
}
