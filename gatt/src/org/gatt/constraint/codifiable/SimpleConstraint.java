package org.gatt.constraint.codifiable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.ConstBooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparison;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;


//Constraint type if !Constraint then return penalty
//The idea is that if the constraint is not true,
//then the penality must be returned.
public class SimpleConstraint implements CodifiableDisplayableObject {
	private CodifiableConstraintValue penality;
	private BooleanOperand cbe;
	
	public SimpleConstraint(BooleanOperand condition, CodifiableConstraintValue penality){
		this.penality = penality;
		cbe = condition;
	}
	
	/**
	 * The idea is: Debe cumplirse que: cbe
	 */
	public String getDisplayName() {
		StringBuffer buffer = new StringBuffer();		
		buffer.append("Debe cumplirse que: ");
		buffer.append(cbe.getDisplayName());		
		return buffer.toString();
	}
	/**
	 * The idea is: Debe cumplirse que: cbe
	 */
	public String getReadableName() {
		StringBuffer buffer = new StringBuffer();		
		buffer.append("Debe cumplirse que: ");
		buffer.append(cbe.getReadableName());		
		return buffer.toString();
	}
	/**
	 * The idea is: if( !(condition) ) return penalty.
	 */
	public String getJavaString() {
		StringBuffer buffer = new StringBuffer();		
		buffer.append("if(!");
		buffer.append(cbe.getJavaString());
		buffer.append(")");
		buffer.append(NL);
		buffer.append("\t");		
		buffer.append("return ");
		buffer.append(penality.getJavaString());
		buffer.append(";");
		buffer.append(NL);
		return buffer.toString();
	}
	public static void main(String[] ar){
		ComparableOperand compOperand1 = new BooleanOperand("chucho", "Value1", "cosa1");
		ComparableOperand compOperand2 = new BooleanOperand("iValue2", "Value2", "cosa2");
		DefaultComparison co = new DefaultComparison(DefaultComparisonOperator.LESS_EQUAL_THAN, compOperand1, compOperand2);
		CompositeBooleanExpression cbe = new CompositeBooleanExpression(BooleanOperator.AND, ConstBooleanOperand.TRUE, co);		
		SimpleConstraint conditional = new SimpleConstraint(cbe, CodifiableConstraintValue.ONE);
		System.out.println(conditional.getJavaString());
	}
}
