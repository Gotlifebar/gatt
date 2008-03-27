package org.gatt.domain;

/**
 * @author Chucho
 * Class Group
 */
public class Group extends DomainObject {
	
	/**
	 * the number of the group
	 */
	private int number;
	
	/**
	 * subject this group belongs to
	 */
	private Subject subject;
	
	/**
	 * number of students of the group
	 */
	private int numStudents;
	//private Teacher teacher;
	
	/**
	 * constructor 
	 */
	public Group(){
		super();
	}
	
	/**
	 * return the number of the group
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * sets the number of the group
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * return the subject this group belongs to
	 */
	public Subject getSubject() {
		return subject;
	}
	
	/**
	 * sets the subject this group belongs to
	 * @param subject
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	/**
	 * returns the number of students of the group
	 */
	public int getNumStudents() {
		return numStudents;
	}
	
	/**
	 * sets the number of students of the group
	 * @param maxCapacity
	 */
	public void setNumStudents(int maxCapacity) {
		this.numStudents = maxCapacity;
	}
/*	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}*/
	
	/**
	 * returns true if a constraint can be applied to this group
	 * @param constraintId
	 */
	public boolean applyConstraint(String constraintId){
		return true;
	}
	
	
}
