package org.gatt.domain.factories;
import org.gatt.domain.Group;

/**
 * @author David
 * Group DAO interface
 */
public interface GroupDAO {
	/**
	 * @param id
	 * Perform the query to find a group in the database
	 */
	public Group findGroup(int id);
	
	/**
	 * return the number of groups
	 */
	public int countGroups();
}
