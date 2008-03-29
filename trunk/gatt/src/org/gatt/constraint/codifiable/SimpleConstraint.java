package org.gatt.constraint.codifiable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.ConstBooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparison;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;



/**
 * This class represents a simple constraint, this is a constraint that evaluates a condition and 
 * if the condition is false then returns a penalty, else, if the condition is met, then 
 * returns no (zero) penalty.
 * @author David
 *
 */
public class SimpleConstraint implements CodifiableDisplayableObject {
	

	/**
	 * Penalty of the constraint 
	 */
	private CodifiableConstraintValue penality;
	
	/**
	 * Internal representation of the constraint
	 */
	private BooleanOperand cbe;
	
	/** Constructur
	 * @param condition condition of the constraint to be evaluated
	 * @param penality penalty value of the constraint
	 */
	public SimpleConstraint(BooleanOperand condition, CodifiableConstraintValue penality){
		this.penality = penality;
		cbe = condition;
	}
	
	
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.CodifiableDisplayableObject#getDisplayName()
	 */
	public String getDisplayName() {
		StringBuffer buffer = new StringBuffer();		
		buffer.append("Debe cumplirse que: ");
		buffer.append(cbe.getDisplayName());		
		return buffer.toString();
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.CodifiableDisplayableObject#getReadableName()
	 */
	public String getReadableName() {
		StringBuffer buffer = new StringBuffer();		
		buffer.append("Debe cumplirse que: ");
		buffer.append(cbe.getReadableName());		
		return buffer.toString();
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.Codifiable#getJavaString()
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
	
}
