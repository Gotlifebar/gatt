package org.gatt.domain.factories;
import java.util.Collection;

import org.gatt.domain.Hour;

public interface HourDAO {
	public Hour findHour(int id);
	public Collection<Hour> findAll();
	public int countHours();
}
