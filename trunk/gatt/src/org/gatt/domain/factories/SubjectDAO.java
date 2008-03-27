package org.gatt.domain.factories;
import org.gatt.domain.Subject;

/**
 * @author Chucho
 * Subject DAO interface
 */
public interface SubjectDAO {
	/**
	 * @param id
	 * Perform the query to find a Subject in the database
	 */
	public Subject findSubject(int id);
	
	/**
	 * @param code
	 * Perform the query to find a Subject in the database by its code
	 */
	public Subject findSubjectByCode(int code);
	
	/**
	 * return the number of subjects in the database
	 */
	public int countSubjects();
}
