package org.gatt.domain.factories;
import org.gatt.domain.*;

public interface MediaTypeDAO {
	public MediaType findMediaType(int id);
	public int countMediaTypes();
}
