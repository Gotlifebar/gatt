package org.gatt.constraint.codifiable.boolexpression;

import org.gatt.constraint.codifiable.Operator;

//It's like an enumeration
public class DefaultComparisonOperator implements Operator {
	public static DefaultComparisonOperator EQUAL = new DefaultComparisonOperator("==", "EQUAL", "es igual a"),
									 LESS_THAN = new DefaultComparisonOperator("<", "LESS_THAN", "es menor que"),
									 LESS_EQUAL_THAN = new DefaultComparisonOperator("<=", "LESS_EQUAL_THAN", "es menor o igual que"),
									 MORE_THAN = new DefaultComparisonOperator(">", "MORE_THAN", "es mayor que"),
									 MORE_EQUAL_THAN = new DefaultComparisonOperator(">=", "MORE_EQUAL_THAN", "es mayor o igual que"),
									 NOT_EQUAL = new DefaultComparisonOperator("!=", "NOT_EQUAL", "es diferente a");
	private String javaString, name, readableName;
	protected DefaultComparisonOperator(String javaString, String name, String readableName){
		this.javaString = javaString;
		this.name = name;
		this.readableName = readableName;
	}
	public String getJavaString() {
		return javaString;
	}
	public String getDisplayName() {
		return name;
	}
	public String getReadableName(){
		return readableName;
	}
}