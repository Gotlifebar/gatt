package org.gatt.constraint.codifiable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.ConstBooleanOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparableOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparison;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;


//Constraint type if ... then
//The idea is that if the antecedent is true, then the consequent has to be true;
//If antecedent is false, it doesn't care
//but if antecedent is true and consequent is false, the penality must be returned.
public class ConditionalConstraint implements CodifiableDisplayableObject{	
	
	private CodifiableConstraintValue penality;
	private BooleanOperand antecedent,  consequent;
	private CompositeBooleanExpression cbe;
	
	
	public ConditionalConstraint(BooleanOperand antecedent, BooleanOperand consequent, CodifiableConstraintValue penality){		
		this.penality = penality;
		cbe = new CompositeBooleanExpression(BooleanOperator.AND, 
											antecedent, 
											new CompositeBooleanExpression(BooleanOperator.NOT, consequent));
		this.antecedent = antecedent;
		this.consequent = consequent;
	}
	
	/* The idea is
	 * if( antecedent AND not consequent )  
	 * 		return penalty;
	 */
	
	public String getJavaString(){
		StringBuffer buffer = new StringBuffer();		
		buffer.append("if");
		buffer.append(cbe.getJavaString());
		//buffer.append("{");
		buffer.append(NL);
		buffer.append("\t");		
		buffer.append("return ");
		buffer.append(penality.getJavaString());
		buffer.append(";");
		buffer.append(NL);
		//buffer.append("}");
		return buffer.toString();
	}
	public String getDisplayName() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Si pasa que: ");
		buffer.append(antecedent.getDisplayName() + ", ");
		buffer.append(NL);
		buffer.append("Entonces debe ocurrir que: ");
		buffer.append(consequent.getDisplayName());		
		return buffer.toString();
	}

	public String getReadableName() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Si pasa que: ");
		buffer.append(antecedent.getDisplayName() + ", ");
		buffer.append(NL);
		buffer.append("Entonces debe ocurrir que: ");
		buffer.append(consequent.getDisplayName());		
		return buffer.toString();
	}
	
	public static void main(String ar[]){
		
		StringComparableOperand compOperand1 = new StringComparableOperand("chucho", "Value1", "cosa1");
		StringComparableOperand compOperand2 = new StringComparableOperand("iValue2", "Value2", "cosa2");
		StringComparison co = new StringComparison(StringComparisonOperator.EQUALS, compOperand1, compOperand2);
		CompositeBooleanExpression cbe = new CompositeBooleanExpression(BooleanOperator.AND, ConstBooleanOperand.TRUE, co);
		CompositeBooleanExpression cbe2 = new CompositeBooleanExpression(BooleanOperator.OR, cbe, cbe);
		ConditionalConstraint conditional = new ConditionalConstraint(co, cbe, CodifiableConstraintValue.ONE);
		System.out.println(conditional.getJavaString());
	}
}
