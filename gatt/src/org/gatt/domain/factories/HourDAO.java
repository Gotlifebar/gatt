package org.gatt.domain.factories;
import java.util.Collection;

import org.gatt.domain.Hour;

/**
 * @author David
 * Hour DAO interface
 */
public interface HourDAO {
	/**
	 * @param id
	 * Perform the query to find a hour in the database
	 */
	public Hour findHour(int id);
	/**
	 * Perform the query to find all the groups in the database
	 */
	public Collection<Hour> findAll();
	/**
	 * return the number of hours in the database
	 */
	public int countHours();
}
