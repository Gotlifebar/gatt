package org.gatt.domain.factories;
import org.gatt.domain.*;

public interface TeacherDAO {
	public Teacher findTeacher(int registerNumber);
	public int countTeachers();
}
