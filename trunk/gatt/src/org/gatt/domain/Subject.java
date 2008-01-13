package org.gatt.domain;

public class Subject extends DomainObject {
	
	private int fac;
	private int dep;
	private String letterCode;
	private String name;
	private int credits;
	private String restrictions;
	
	public Subject(){
		super();
	}

	public int getFac(){
		return this.fac;
	}
	public int getDep(){
		return this.dep;
	}
	public String getLetterCode(){
		return this.letterCode;
	}
	public String getName(){
		return this.name;
	}
	public int getCredits(){
		return this.credits;
	}
	public String getRestrictions(){
		return this.restrictions;
	}	

	public void setFac(int fac){
		this.fac = fac;
	}
	public void setDep(int dep){
		this.dep = dep;
	}
	public void setLetterCode(String letterCode){
		this.letterCode = letterCode;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setCredits(int credits){
		this.credits = credits;
	}
	public void setRestrictions(String restrictions){
		this.restrictions = restrictions;
	}	
	
}