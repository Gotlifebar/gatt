package org.gatt.constraint;

import java.util.Vector;

public class ConstraintInfo {
	
	private String id;
	private String name;
	private String description;
	private double significance;
	private String strategyCodeImplementation;	
	//private Vector<Character> usedVars;
	
	
	public static String CONSTRAINT_CLASS_PREFIX = "Constraint_";

	public ConstraintInfo(){
	}
	
	public String getSubClassName(){
		return CONSTRAINT_CLASS_PREFIX + id;
	}
	
	public void setStrategyCodeImplementation(String codeImplementation) {
		this.strategyCodeImplementation = codeImplementation;
	}
	
	public String getStrategyCodeImplementation(){
		return strategyCodeImplementation;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public double getSignificance() {
		return significance;
	}

	public void setSignificance(double significance) {
		this.significance = significance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}