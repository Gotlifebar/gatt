package org.gatt.ui.dialogs.helper;

import org.gatt.domain.Hour;

/**
 * @author Chucho
 * Class that wraps the information of a day object
 */
public class DayWrapper {
	
	
	/**
	 * corresponding number of the day 
	 */
	int day;
	
	/**
	 * Array with the name of the days
	 */
	private String[] days = {"LUNES","MARTES",
							"MIÉRCOLES","JUEVES",
							"VIERNES","SÁBADO"};
	
	/**
	 * constructor
	 * @param day
	 */
	public DayWrapper(int day){
		this.day = day;
	}
	
	/**
	 * return the number of the day
	 */
	public int getDay(){
		return day;
	}
	
	/**
	 * return the string representation of the days
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return days[day-1];
	}
}
