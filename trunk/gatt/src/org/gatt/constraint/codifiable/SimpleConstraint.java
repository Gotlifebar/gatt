package org.gatt.constraint.codifiable;

import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;


//Constraint type if !Constraint then return penalty
//The idea is that if the constraint is not true,
//then the penality must be returned.
public class SimpleConstraint implements CodifiableDisplayableObject {
	private CodifiableConstraintValue penality;
	private CompositeBooleanExpression cbe;
	
	public SimpleConstraint(CompositeBooleanExpression condition, CodifiableConstraintValue penality){
		this.penality = penality;
		cbe = condition;
	}
	
	/**
	 * The idea is: Debe cumplirse que: cbe
	 */
	public String getDisplayName() {
		StringBuffer buffer = new StringBuffer();		
		buffer.append("Debe cumplirse que: ");
		buffer.append(cbe.getJavaString());		
		return buffer.toString();
	}
	/**
	 * The idea is: Debe cumplirse que: cbe
	 */
	public String getReadableName() {
		StringBuffer buffer = new StringBuffer();		
		buffer.append("Debe cumplirse que: ");
		buffer.append(cbe.getJavaString());		
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
	/*	ComparableOperand compOperand1 = new ComparableOperand("chucho", "Value1", "cosa1");
		ComparableOperand compOperand2 = new ComparableOperand("iValue2", "Value2", "cosa2");
		Comparison co = new Comparison(DefaultComparisonOperator.LESS_EQUAL_THAN, compOperand1, compOperand2);
		CompositeBooleanExpression cbe = new CompositeBooleanExpression(BooleanOperator.AND, ConstBooleanOperand.TRUE, co);		
		SimpleConstraint conditional = new SimpleConstraint(cbe, CodifiableConstraintValue.ONE);
		System.out.println(conditional.getJavaString());*/
	}
}
