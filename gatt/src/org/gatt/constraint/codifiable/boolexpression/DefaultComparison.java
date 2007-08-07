package org.gatt.constraint.codifiable.boolexpression;


public class DefaultComparison extends BooleanOperand{
	private ComparableOperand left, right;
	private DefaultComparisonOperator operator;
	
	public DefaultComparison(DefaultComparisonOperator operator, ComparableOperand left, ComparableOperand right ){
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	public String getDisplayName() {
		return left.getDisplayName() + " " + operator.getDisplayName() + " " + right.getDisplayName(); 
	}

	public String getJavaString() {
		return "(" + left.getJavaString() + " " + operator.getJavaString() + " " + right.getJavaString() + ")";
	}
	public String getReadableName(){
		return left.getReadableName() + " " + operator.getReadableName() + " " + right.getReadableName();
	}
}