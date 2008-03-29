package org.gatt.constraint.codifiable.stringexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;

/**
 * This class represents an operator that can be used in a comparison of two strings. 
 * This class acts as an enumeration and holds the possible operators that can be used in the comparison. 
 * The only operator supported yet is the EQUALS operator.
 * @author David
 *
 */
public class StringComparison extends BooleanOperand implements Serializable {
	/**
	 * Compared operands
	 */
	private ComparableOperand left, right;
	
	/**
	 * Comparison operator
	 */
	private StringComparisonOperator operator;
	
	/** Constructor
	 * @param operator comparison operator
	 * @param left compared left operand
	 * @param right compared right operand
	 */
	public StringComparison(StringComparisonOperator operator, StringComparableOperand left, StringComparableOperand right) {
		this.operator = operator;
		this.left = left;
		this.right = right;		
	}
	
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getDisplayName()
	 */
	public String getDisplayName() {
		return left.getDisplayName() + operator.getDisplayName() + right.getDisplayName(); 
	}

	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getJavaString()
	 */
	public String getJavaString() {
		if( operator == StringComparisonOperator.NOT_EQUAL )
			return "!(" + left.getJavaString() + operator.getJavaString() + "( " + right.getJavaString() + ") )";
		return "(" + left.getJavaString() + operator.getJavaString() + "( " + right.getJavaString() + ") )";
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getReadableName()
	 */
	public String getReadableName(){
		return left.getReadableName() + " " + operator.getReadableName() + " " + right.getReadableName();
	}
}
