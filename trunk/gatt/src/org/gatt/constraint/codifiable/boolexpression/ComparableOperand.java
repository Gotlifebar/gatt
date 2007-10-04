package org.gatt.constraint.codifiable.boolexpression;

import java.io.Serializable;

import org.gatt.constraint.codifiable.Operand;


public abstract class ComparableOperand implements Operand, Serializable {
	private String javaString, name, readableName;
	
	
	public ComparableOperand(String javaString, String name, String readableName){
		this.javaString = javaString;
		this.name = name;
		this.readableName = readableName;
	}
	public ComparableOperand(){
		
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
	public void setJavaString(String javaString) {
		this.javaString = javaString;
	}
	public void setDisplayName(String name) {
		this.name = name;
	}
	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}
}
