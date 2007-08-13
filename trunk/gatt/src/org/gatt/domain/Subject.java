package org.gatt.domain;

public class Subject extends DomainObject {
	
	private String code;
	private String letterCode;
	private String name;
	private int theoricalHours;
	private int practicalHours;
	//private int program;
	//private int semester;
	
	public Subject(){
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLetterCode() {
		return letterCode;
	}

	public void setLetterCode(String letterCode) {
		this.letterCode = letterCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTheoricalHours() {
		return theoricalHours;
	}

	public void setTheoricalHours(int theoricalHours) {
		this.theoricalHours = theoricalHours;
	}

	public int getPracticalHours() {
		return practicalHours;
	}

	public void setPracticalHours(int practicalHours) {
		this.practicalHours = practicalHours;
	}

	/*public int getProgram() {
		return program;
	}

	public void setProgram(int program) {
		this.program = program;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}*/

	
}