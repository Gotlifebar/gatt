package org.gatt.constraint.codifiable.boolexpression;

import org.gatt.constraint.codifiable.Operand;


public abstract class ComparableOperand implements Operand {
	private String javaString, name, readableName;
	
	public ComparableOperand(String javaString, String name, String readableName){
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
