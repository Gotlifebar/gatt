package org.gatt.domain.factories;
import org.gatt.domain.Group;

public interface GroupDAO {
	public Group findGroup(int id);
	public int countGroups();
}
