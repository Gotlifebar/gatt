package org.gatt.ui.dialogs.helper;

import org.gatt.domain.Hour;

public class DayWrapper {
	
	int day;
	
	private String[] days = {"LUNES","MARTES",
							"MI�RCOLES","JUEVES",
							"VIERNES","S�BADO"};
	
	public DayWrapper(int day){
		this.day = day;
	}
	
	public int getDay(){
		return day;
	}
	
	public String toString(){
		return days[day-1];
	}
}
