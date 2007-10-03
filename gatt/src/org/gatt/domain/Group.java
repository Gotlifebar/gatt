package org.gatt.domain;

public class Group extends DomainObject {
	
	private int number;
	private Subject subject;
	private int maxCapacity;
	private Teacher teacher;
	
	public Group(){
		super();
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public boolean applyConstraint(String constraintId){
		return true;
	}
	
	
}
