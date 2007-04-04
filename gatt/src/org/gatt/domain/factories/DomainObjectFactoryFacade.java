package org.gatt.domain.factories;
import org.gatt.domain.*;

public class DomainObjectFactoryFacade {
	
	DAOFactory factory;	
	public void setDAOFactoryClass(String daoFactoryClass) 
		throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		factory = (DAOFactory) ClassLoader.getSystemClassLoader().loadClass(daoFactoryClass).newInstance();
	}
	
	public Group getGroup(int id){
		return factory.getGroupDAO().findGroup(id);
	}
	public Hour getHour(int id){
		return factory.getHourDAO().findHour(id);
	}
	public MediaType getMediaType(int id){
		return factory.getMediaTypeDAO().findMediaType(id);
	}
	public Room getRoom(int id){
		return factory.getRoomDAO().findRoom(id);
	}
	public Session getSession(int id){
		return factory.getSessionDAO().findSession(id);
	}
	public Subject getSubject(int id){
		return factory.getSubjectDAO().findSubject(id);
	}
	public Teacher getTeacher(int id){
		return factory.getTeacherDAO().findTeacher(id);
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
