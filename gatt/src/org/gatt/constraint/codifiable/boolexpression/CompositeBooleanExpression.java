package org.gatt.constraint.codifiable.boolexpression;

public class CompositeBooleanExpression extends BooleanOperand {
	private BooleanOperand left, right;
	private BooleanOperator operator;
	
	public CompositeBooleanExpression(BooleanOperator operator, BooleanOperand left, BooleanOperand right ){
		this.operator = operator;		
		this.left = left;
		this.right = right;
	}
	public CompositeBooleanExpression(BooleanOperator operator, BooleanOperand left){
		this.operator = operator;
		this.left = left;
		this.right = null;
	}
	public String getDisplayName() {
		if( right == null )
			return operator.getDisplayName() + left.getDisplayName();
		return left.getDisplayName() + operator.getDisplayName() + right.getDisplayName(); 
	}

	public String getJavaString() {
		if( right == null )
			return "(" + operator.getJavaString() + left.getJavaString() + ")";
		return "(" + left.getJavaString() + " " + operator.getJavaString() + " " + right.getJavaString() + ")";
	}
	public String getReadableName(){
		if( right == null )
			return operator.getReadableName() + " " + left.getReadableName();  
		return left.getReadableName() + " " + operator.getReadableName() + " " + right.getReadableName();
	}
}
