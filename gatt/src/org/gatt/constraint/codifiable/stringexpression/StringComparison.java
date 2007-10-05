package org.gatt.constraint.codifiable.stringexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparison;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;

public class StringComparison extends BooleanOperand implements Serializable {
	private ComparableOperand left, right;
	private StringComparisonOperator operator;
	
	
	public StringComparison(StringComparisonOperator operator, StringComparableOperand left, StringComparableOperand right) {
		this.operator = operator;
		this.left = left;
		this.right = right;		
	}
	public String getDisplayName() {
		return left.getDisplayName() + operator.getDisplayName() + right.getDisplayName(); 
	}

	public String getJavaString() {
		if( operator == StringComparisonOperator.NOT_EQUAL )
			return "!(" + left.getJavaString() + operator.getJavaString() + "( " + right.getJavaString() + ") )";
		return "(" + left.getJavaString() + operator.getJavaString() + "( " + right.getJavaString() + ") )";
	}
	public String getReadableName(){
		return left.getReadableName() + " " + operator.getReadableName() + " " + right.getReadableName();
	}
}
