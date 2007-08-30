package org.gatt.optimization.util;

public class NumericTransformationFunction {
	
	private int countRooms, countHours;
	
	public NumericTransformationFunction(int countRooms, int countHours) {
		this.countRooms = countRooms;
		this.countHours = countHours;
	}
	public int getIndexFor(int idRoom, int idHour){
	//	idRoom--;
	//	idHour--;
		return idRoom + idHour * countRooms;
	}
	public int getRoomIdFrom(int index){		
		return index % countRooms ;  	
	}
	public int getHourIdFrom(int index){		
		return index / countRooms;
	}
	public int getTotalIndex(){
		return countRooms * countHours;
	}
	
	
}
