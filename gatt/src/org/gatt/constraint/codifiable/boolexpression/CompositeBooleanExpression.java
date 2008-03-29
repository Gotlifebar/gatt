package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

/**
 * this class represents a composed Boolean expression, 
 * which is an expression with a left hand operand, an operator and a right hand operand. 
 * The right hand operand is optional, because the operator NOT is unary.
 * @author David
 *
 */
public class CompositeBooleanExpression extends BooleanOperand implements Serializable {
	
	/**
	 * Operands that compose the expression
	 */
	private BooleanOperand left, right;
	
	/**
	 * Operator between the operands
	 */
	private BooleanOperator operator;
	
	/** Constructor of a binary operation
	 * @param operator operator
	 * @param left left operand
	 * @param right right operand
	 */
	public CompositeBooleanExpression(BooleanOperator operator, BooleanOperand left, BooleanOperand right ){
		this.operator = operator;		
		this.left = left;
		this.right = right;
	}
	
	/** Constructor of an unary operation
	 * @param operator operator
	 * @param left only operand
	 */
	public CompositeBooleanExpression(BooleanOperator operator, BooleanOperand left){
		this.operator = operator;
		this.left = left;
		this.right = null;
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getDisplayName()
	 */
	public String getDisplayName() {
		if( right == null )
			return operator.getDisplayName() + left.getDisplayName();
		return left.getDisplayName() + operator.getDisplayName() + right.getDisplayName(); 
	}

	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getJavaString()
	 */
	public String getJavaString() {
		if( right == null )
			return "(" + operator.getJavaString() + left.getJavaString() + ")";
		return "(" + left.getJavaString() + " " + operator.getJavaString() + " " + right.getJavaString() + ")";
	}
	/* (non-Javadoc)
	 * @see org.gatt.constraint.codifiable.boolexpression.ComparableOperand#getReadableName()
	 */
	public String getReadableName(){
		if( right == null )
			return operator.getReadableName() + " " + left.getReadableName();  
		return left.getReadableName() + " " + operator.getReadableName() + " " + right.getReadableName();
	}
}
