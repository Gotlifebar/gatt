package org.gatt.optimization.util;

import java.io.Serializable;

/**
 * @author david
 * Numeric transformation
 */
public class NumericTransformationFunction implements Serializable {
	
	/**
	 * number of rooms
	 */
	private int countRooms;
	/**
	 * number of hours
	 */ 
	private int countHours;
	
	/**
	 * constructor
	 * @param countRooms
	 * @param countHours
	 */
	public NumericTransformationFunction(int countRooms, int countHours) {
		this.countRooms = countRooms;
		this.countHours = countHours;
	}
	/**
	 * perform a numeric transformation
	 * @param idRoom
	 * @param idHour
	 */
	public int getIndexFor(int idRoom, int idHour){
	//	idRoom--;
	//	idHour--;
		return idRoom + idHour * countRooms;
	}
	/**
	 * perform the inverse numeric transformation in order to get a room
	 * @param index
	 */
	public int getRoomIdFrom(int index){		
		return index % countRooms ;  	
	}
	/**
	 * @param index
	 * perform the inverse numeric transformation in order to get a hour
	 */
	public int getHourIdFrom(int index){		
		return index / countRooms;
	}
	/**
	 * return the number of possible indexes 
	 */
	public int getTotalIndex(){
		return countRooms * countHours;
	}
	
	
}
