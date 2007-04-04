package org.gatt.domain.factories;
import org.gatt.domain.*;

public interface TeacherDAO {
	public Teacher findTeacher(int id);
	public int countTeachers();
}
