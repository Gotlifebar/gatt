package org.gatt.domain.factories;

import java.util.Collection;
import org.gatt.domain.InitialTT;

/**
 * @author Chucho
 * InitialTT DAO interface
 */
public interface InitialTTDAO{
	/**
	 * @param id
	 * Perform the query to find an InitialTT in the database
	 */
	public InitialTT findInitialTT(int id);
	/**
	 * Perform the query to find all the InitialTT in the database
	 */
	public Collection<InitialTT> findAll();
	/**
	 * return the number of initial timetabling
	 */
	public int countInitial_Timetabling();
}