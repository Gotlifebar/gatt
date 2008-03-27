package org.gatt.domain.factories;
import org.gatt.domain.Teacher;

/**
 * @author Chucho
 * Teacher DAO Interface
 */
public interface TeacherDAO {
	/**
	 * @param registerNumber
	 * Perform the query to find a Teacher in the database
	 */
	public Teacher findTeacher(int registerNumber);
	/**
	 * return the number of teachers in the database
	 */
	public int countTeachers();
}
