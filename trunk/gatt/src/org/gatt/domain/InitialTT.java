package org.gatt.domain;
/**
 * @author david
 * represents an initial timetabling solution
 */
public class InitialTT{

		/**
		 * group id
		 */
		private int group;
		/**
		 * room id
		 */
		private int room;
		/**
		 * hour id
		 */
		private int hour;
	
		/**
		 * return the group id
		 */
		public int getGroup(){
			return this.group;
		}
		/**
		 * return the room id
		 */
		public int getRoom(){
			return this.room;
		}
		/**
		 * return the hour id
		 */
		public int getHour(){
			return this.hour;
		}
	
		/**
		 * sets the group id
		 * @param group
		 */
		public void setGroup(int group){
			this.group = group;
		}
		/**
		 * sets the room id
		 * @param room
		 */
		public void setRoom(int room){
			this.room = room;
		}
		/**
		 * sets the hour id
		 * @param hour
		 */
		public void setHour(int hour){
			this.hour = hour;
		}
}