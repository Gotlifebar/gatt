package org.gatt.domain.factories;
import org.gatt.domain.*;

public class DomainObjectFactoryFacade {
	private Repository cache;
	private boolean useCache;
	
	//Singleton Implementation
	private static DomainObjectFactoryFacade instance = null;
	private DAOFactory factory;
	public static DomainObjectFactoryFacade getInstance(){
		if( instance == null )
			instance = new DomainObjectFactoryFacade();
		return instance;
	}
	
	private DomainObjectFactoryFacade(){
		//TODO: These values should be loaded from a config file
		cache = new HashMapRepository();
		useCache = true;
	}	
	public void setDAOFactoryClass(String daoFactoryClass) 
		throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		factory = DAOFactory.getDAOFactory(daoFactoryClass);
	}
	
	public Group getGroup(int id){
		if( !useCache )
			return factory.getGroupDAO().findGroup(id);
		
		Group g = (Group)cache.getObjectById(id, Group.class); 
		if( g == null ){
			g = factory.getGroupDAO().findGroup(id);
			cache.addObject(g);
		}
		return g; 
	}
	public int getGroupsCount(){
		return factory.getGroupDAO().countGroups();
	}
	public Hour getHour(int id){
		if( !useCache )
			return factory.getHourDAO().findHour(id);
		
		Hour h = (Hour)cache.getObjectById(id, Hour.class);
		if( h == null ){
			h = factory.getHourDAO().findHour(id);
			cache.addObject(h);
		}
		return h;
	}
	public int getHoursCount(){
		return factory.getHourDAO().countHours();
	}
	public MediaType getMediaType(int id){
		if( !useCache )
			return factory.getMediaTypeDAO().findMediaType(id);
		
		MediaType m = (MediaType)cache.getObjectById(id, MediaType.class);
		if( m == null ){
			m = factory.getMediaTypeDAO().findMediaType(id);
			cache.addObject(m);
		}
		return m;
	}
	public int getMediaTypesCount(){
		return factory.getMediaTypeDAO().countMediaTypes();
	}
	public Room getRoom(int id){
		if( !useCache )
			return factory.getRoomDAO().findRoom(id);
		
		Room r = (Room)cache.getObjectById(id, Room.class);
		if( r == null ){
			r = factory.getRoomDAO().findRoom(id);
			cache.addObject(r);
		}
		return r;
	}
	public int getRoomsCount(){
		return factory.getRoomDAO().countRooms();
	}
	public Session getSession(int id){
		if( !useCache )
			return factory.getSessionDAO().findSession(id);
		
		Session s = (Session)cache.getObjectById(id, Session.class);
		if( s == null ){
			s = factory.getSessionDAO().findSession(id);
			cache.addObject(s);
		}
		return s;
	}
	public int getSessionsCount(){
		return factory.getSessionDAO().countSessions();
	}
	public Subject getSubject(int id){
		if( !useCache )
			return factory.getSubjectDAO().findSubject(id);
		
		Subject s = (Subject)cache.getObjectById(id, Subject.class);
		if( s == null ){
			s = factory.getSubjectDAO().findSubject(id);
			cache.addObject(s);
		}
		return s;
	}
	public Subject getSubjectByCode(int id){
		if( !useCache )
			return factory.getSubjectDAO().findSubjectByCode(id);
		
		Subject s = (Subject)cache.getObjectById(id, Subject.class);
		if( s == null ){
			s = factory.getSubjectDAO().findSubjectByCode(id);
			cache.addObject(s);
		}
		return s;
	}
	public int getSubjectsCount(){
		return factory.getSubjectDAO().countSubjects();
	}
	public Teacher getTeacher(int id){
		if( !useCache )
			return factory.getTeacherDAO().findTeacher(id);
		
		Teacher t = (Teacher)cache.getObjectById(id, Teacher.class);
		if( t == null ){
			t = factory.getTeacherDAO().findTeacher(id);
			cache.addObject(t);
		}
		return t;		
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
		int count = doff.getSubjectsCount();
		System.out.println(count);
		//Subject s = doff.getSubject(1);
		//System.out.println(s.getName());
	}
}
