package org.gatt.domain.factories;
import org.gatt.domain.MediaType;

/**
 * @author Chucho
 * MediaType DAO Interface
 */
public interface MediaTypeDAO {
	/**
	 * @param id
	 * Perform the query to find a MediaType in the database
	 */
	public MediaType findMediaType(int id);
	
	/**
	 * return the number of MediaType in the database
	 */
	public int countMediaTypes();
}
