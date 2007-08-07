package org.gatt.constraint.codifiable.boolexpression;

public class ConstBooleanOperand extends BooleanOperand {	
	
	public static ConstBooleanOperand TRUE = new ConstBooleanOperand("true", "True", "verdadero");
	public static ConstBooleanOperand FALSE = new ConstBooleanOperand("false", "False", "falso");	
	
	private String javaString, name, readableName;
	private ConstBooleanOperand(String javaString, String name, String readable){
		this.javaString = javaString;
		this.name = name;
		this.readableName = readable;
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