package org.gatt.constraint.codifiable.stringexpression;

import org.gatt.constraint.codifiable.Operator;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;


public class StringComparisonOperator implements Operator{

	private String displayName;
	private String readableName;
	private String javaString;
	
	public static StringComparisonOperator EQUALS = 
		new StringComparisonOperator(".equals", "EQUALS", "IGUAL A " );
	
	
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
}
