package org.gatt.domain.factories;
import org.gatt.domain.*;

public interface SubjectDAO {
	public Subject findSubject(int id);
	public Subject findSubjectByCode(int code);
	public int countSubjects();
}
