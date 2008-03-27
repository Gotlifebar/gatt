package org.gatt.constraint;


import java.io.Serializable;


/**
 * @author David
 * Class that contains the general information of a constraint
 */
public class ConstraintInfo implements Serializable {
	
	/**
	 * Constraint id
	 */
	private String id;
	
	/**
	 * Constraint name 
	 */
	private String name;
	
	/**
	 * Constraint description 
	 */
	private String description;
	
	/**
	 * Constraint significance
	 */
	private double significance;
	
	/**
	 * Constraint implementation strategy
	 */
	private String strategyCodeImplementation;
	
	/**
	 * Constraint definition 
	 */
	private String definition;
	//private Vector<Character> usedVars;
	
	
	/**
	 * Constraint generated class prefix
	 */
	public static String CONSTRAINT_CLASS_PREFIX = "Constraint_";

	/**
	 * Constructor
	 */
	public ConstraintInfo(){
	}
	
	
	/**
	 * return the constraint class name
	 */
	public String getSubClassName(){
		return CONSTRAINT_CLASS_PREFIX + id;
	}
	
	/**
	 * sets the code implementation strategy
	 * @param codeImplementation
	 */
	public void setStrategyCodeImplementation(String codeImplementation) {
		this.strategyCodeImplementation = codeImplementation;
	}
	
	/**
	 * returns the code implementation strategy
	 */
	public String getStrategyCodeImplementation(){
		return strategyCodeImplementation;
	}
	
	/**
	 * returns the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets the constraint description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * return the constraint id
	 */
	public String getId() {
		return id;
	}

	/**
	 * sets the constraint id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * returns the constraint significance
	 */
	public double getSignificance() {
		return significance;
	}

	/**
	 * sets the constraint significance
	 * @param significance
	 */
	public void setSignificance(double significance) {
		this.significance = significance;
	}

	/**
	 * return constraint name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets constraint name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns constraint definition
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * sets constraint definition
	 * @param definition
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}	
}