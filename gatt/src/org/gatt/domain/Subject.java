package org.gatt.domain;

/**
 * @author Chucho
 * Class Subject
 */
public class Subject extends DomainObject {
	
	/**
	 * faculty
	 */
	private int fac;
	/**
	 * department
	 */
	private int dep;
	/**
	 * letters code
	 */
	private String letterCode;
	/**
	 * name of the subject
	 */
	private String name;
	/**
	 * number of credits of the subject
	 */
	private int credits;
	/**
	 * restrictions of the subject
	 */
	private String restrictions;
	
	/**
	 * constructor
	 */
	public Subject(){
		super();
	}

	/**
	 * returns the faculty
	 */
	public int getFac(){
		return this.fac;
	}
	/**
	 * returns the department
	 */
	public int getDep(){
		return this.dep;
	}
	/**
	 * returns the letters code
	 */
	public String getLetterCode(){
		return this.letterCode;
	}
	/**
	 * return the name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * return the number of credits
	 */
	public int getCredits(){
		return this.credits;
	}
	/**
	 * return the restrictions of the subject
	 */
	public String getRestrictions(){
		return this.restrictions;
	}	

	/**
	 * sets the faculty
	 * @param fac
	 */
	public void setFac(int fac){
		this.fac = fac;
	}
	/**
	 * sets the department
	 * @param dep
	 */
	public void setDep(int dep){
		this.dep = dep;
	}
	/**
	 * sets the letters code
	 * @param letterCode
	 */
	public void setLetterCode(String letterCode){
		this.letterCode = letterCode;
	}
	/**
	 * sets the name
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * sets the number of credits
	 * @param credits
	 */
	public void setCredits(int credits){
		this.credits = credits;
	}
	/**
	 * sets the restrictions
	 * @param restrictions
	 */
	public void setRestrictions(String restrictions){
		this.restrictions = restrictions;
	}	
	
}