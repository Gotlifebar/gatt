package org.gatt.constraint.codifiable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.ConstBooleanOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparableOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparison;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;


//Constraint type if ... then
//The idea is that if the antecedent is true, then the consequent has to be true;
//If antecedent is false, it doesn't care
//but if antecedent is true and consequent is false, the penality must be returned.

/** 
 * Represents a conditional constraint, this is a constraint of the type “if antecedent then consequent” that is, 
 * if an antecedent is met, the consequent must be true
 * @author David *
 */
public class ConditionalConstraint implements CodifiableDisplayableObject{	
	
	/** 
	 * Penalty of the constraint 
	 */
	private CodifiableConstraintValue penality;
	
	/** 
	 * Antecedent part of the conditional	  
	 */
	
	private BooleanOperand antecedent;
	/**
	 * Consequent part of the conditional 
	 */
	private BooleanOperand consequent;
	
	/**
	 * Internal representation of the constraint
	 */
	private CompositeBooleanExpression cbe;
	
	
	/** Constructur
	 * @param antecedent antecedent part of the constraint
	 * @param consequent consequent part of the constraint
	 * @param penality penalty of the constraint
	 */
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
	
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.Codifiable#getJavaString()
	 */
	public String getJavaString(){
		StringBuffer buffer = new StringBuffer();		
		buffer.append("if");
		buffer.append(cbe.getJavaString());
		//buffer.append("{");
		buffer.append(NL);
		buffer.append("\t");
		buffer.append("cont++");
		//buffer.append("return ");
		//buffer.append(penality.getJavaString());
		buffer.append(";");
		buffer.append(NL);
		//buffer.append("}");
		return buffer.toString();
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.CodifiableDisplayableObject#getDisplayName()
	 */
	public String getDisplayName() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Si pasa que: ");
		buffer.append(antecedent.getDisplayName() + ", ");
		buffer.append(NL);
		buffer.append("Entonces debe ocurrir que: ");
		buffer.append(consequent.getDisplayName());		
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.CodifiableDisplayableObject#getReadableName()
	 */
	public String getReadableName() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Si pasa que: ");
		buffer.append(antecedent.getDisplayName() + ", ");
		buffer.append(NL);
		buffer.append("Entonces debe ocurrir que: ");
		buffer.append(consequent.getDisplayName());		
		return buffer.toString();
	}
	
}
