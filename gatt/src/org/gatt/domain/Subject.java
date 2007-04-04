package org.gatt.domain;

public class Subject extends DomainObject {
	private String code;
	private String name;
	private int program;
	private int semester;
	
	public Subject(){		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProgram() {
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
	}
}