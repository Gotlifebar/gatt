package org.gatt.domain.factories;
import org.gatt.domain.MediaType;

public interface MediaTypeDAO {
	public MediaType findMediaType(int id);
	public int countMediaTypes();
}
