package org.gatt.domain.factories;
import org.gatt.domain.*;

public class DomainObjectFactoryFacade {
	
	DAOFactory factory;	
	public void setDAOFactoryClass(String daoFactoryClass) 
		throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		factory = DAOFactory.getDAOFactory(daoFactoryClass);
	}
	
	public Group getGroup(int id){
		return factory.getGroupDAO().findGroup(id);
	}
	public int getGroupsCount(){
		return factory.getGroupDAO().countGroups();
	}
	public Hour getHour(int id){
		return factory.getHourDAO().findHour(id);
	}
	public int getHoursCount(){
		return factory.getHourDAO().countHours();
	}
	public MediaType getMediaType(int id){
		return factory.getMediaTypeDAO().findMediaType(id);
	}
	public int getMediaTypesCount(){
		return factory.getMediaTypeDAO().countMediaTypes();
	}
	public Room getRoom(int id){
		return factory.getRoomDAO().findRoom(id);
	}
	public int getRoomsCount(){
		return factory.getRoomDAO().countRooms();
	}
	public Session getSession(int id){
		return factory.getSessionDAO().findSession(id);
	}
	public int getSessionsCount(){
		return factory.getSessionDAO().countSessions();
	}
	public Subject getSubject(int id){
		return factory.getSubjectDAO().findSubject(id);
	}
	public int getSubjectsCount(){
		return factory.getSubjectDAO().countSubjects();
	}
	public Teacher getTeacher(int id){
		return factory.getTeacherDAO().findTeacher(id);
	}
	public int getTeachersCount(){
		return factory.getTeacherDAO().countTeachers();
	}
	public static void main(String ar[]){
		DomainObjectFactoryFacade doff = new DomainObjectFactoryFacade();
		try{
			doff.setDAOFactoryClass("org.gatt.domain.factories.mysqldaofactory.MySqlDAOFactory");
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		Subject s = doff.getSubject(1);
		System.out.println(s.getName());
	}
}
