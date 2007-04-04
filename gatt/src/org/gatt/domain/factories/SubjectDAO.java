package org.gatt.domain.factories;
import org.gatt.domain.*;

public interface SubjectDAO {
	public Subject findSubject(int id);
	public int countSubjects();
}
