package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;


/**
 * This class represents a comparison between two ComparableOperands and a ComparisonOperator, 
 * it acts as a composite of these three elements.
 * @author David
 *
 */
public class DefaultComparison extends BooleanOperand implements Serializable{
	
	/**
	 * Compared operands
	 */
	private ComparableOperand left, right;
	
	/**
	 * Comparison operator
	 */
	private DefaultComparisonOperator operator;
	
	/** Constructor
	 * @param operator comparison operator
	 * @param left compared left operand
	 * @param right compared right operand
	 */
	public DefaultComparison(DefaultComparisonOperator operator, ComparableOperand left, ComparableOperand right ){
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getDisplayName()
	 */
	public String getDisplayName() {
		return left.getDisplayName() + " " + operator.getDisplayName() + " " + right.getDisplayName(); 
	}

	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getJavaString()
	 */
	public String getJavaString() {
		return "(" + left.getJavaString() + " " + operator.getJavaString() + " " + right.getJavaString() + ")";
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getReadableName()
	 */
	public String getReadableName(){
		return left.getReadableName() + " " + operator.getReadableName() + " " + right.getReadableName();
	}
}