package org.gatt.constraint.codifiable.stringexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operator;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;


public class StringComparisonOperator implements Operator, Serializable{

	private String displayName;
	private String readableName;
	private String javaString;
	
	public static StringComparisonOperator EQUALS = 
		new StringComparisonOperator(".equals", "EQUALS", "IGUAL A " );
	public static StringComparisonOperator NOT_EQUAL = 
		new StringComparisonOperator(".equals", "NOT_EQUAL", "DIFERENTE DE ");
	
	
	private StringComparisonOperator(String javaString, String displayName, String readableName ) {				
		this.javaString = javaString;
		this.displayName = displayName;
		this.readableName = readableName;
	}
	public String getJavaString() {
		return javaString;
	}
	public String getDisplayName() {
		return displayName;
	}
	public String getReadableName(){
		return readableName;
	}
	public String toString(){
		return readableName;
	}
}
