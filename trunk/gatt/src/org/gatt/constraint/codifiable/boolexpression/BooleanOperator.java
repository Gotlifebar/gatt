package org.gatt.constraint.codifiable.boolexpression;

import org.gatt.constraint.codifiable.Operator;

public class BooleanOperator implements Operator {
	public static BooleanOperator AND = new BooleanOperator("&&", "AND", "y"),
								  OR = new BooleanOperator("||", "OR", "o"),
								  NOT = new BooleanOperator("!", "NOT", "no");
	private String javaString, name, readableName;
	private BooleanOperator(String javaString, String name, String readableName){
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