package org.gatt.optimization.util;

public class NumericTransformationFunction {
	private int countSessions, countRooms;
	
	public NumericTransformationFunction(int countSessions, int countRooms){
		this.countSessions = countSessions;
		this.countRooms = countRooms;
	}
	
	public int transformIndex(int idSession, int idRoom){		
		return idSession + idRoom * countSessions;
	}
	public int obtainSessionIdFrom(int value){
		int sessionId = value % countSessions;
		return sessionId;
	}
	public int obtainRoomIdFrom(int value){
		int roomId = value / countSessions;
		return roomId;
	}
}
