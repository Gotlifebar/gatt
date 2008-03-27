package org.gatt.domain.factories;
import java.util.Collection;

import org.gatt.domain.Group;
import org.gatt.domain.HashMapRepository;
import org.gatt.domain.Hour;
import org.gatt.domain.InitialTT;
import org.gatt.domain.MediaType;
import org.gatt.domain.Repository;
import org.gatt.domain.Room;
import org.gatt.domain.Session;
import org.gatt.domain.Subject;
import org.gatt.domain.Teacher;


/**
 * @author david
 * A facade that provides services for getting domain objects information from the database
 */
public class DomainObjectFactoryFacade {
	
	/**
	 * a repository
	 */
	private Repository cache;
	/**
	 * indicates whether to use cache or not
	 */
	private boolean useCache;
	
	//Singleton Implementation
	/**
	 * Singleton implementation instance
	 */
	private static DomainObjectFactoryFacade instance = null;
	/**
	 * DAO factory
	 */
	private DAOFactory factory;
	/**
	 * return the instance
	 */
	public static DomainObjectFactoryFacade getInstance(){
		if( instance == null )
			instance = new DomainObjectFactoryFacade();
		return instance;
	}
	
	/**
	 * constructor
	 */
	private DomainObjectFactoryFacade(){
		//TODO: These values should be loaded from a config file
		cache = new HashMapRepository();
		useCache = true;
	}	
	/**
	 * sets the DAO factory class
	 * @param daoFactoryClass
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void setDAOFactoryClass(String daoFactoryClass) 
		throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		factory = DAOFactory.getDAOFactory(daoFactoryClass);
	}
	
	/**
	 * @param id
	 * return a group from the database given its id
	 */
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
	/**
	 * return the number of groups in the database
	 */
	public int getGroupsCount(){
		return factory.getGroupDAO().countGroups();
	}
	/**
	 * return a hour object from the database given its id
	 * @param id
	 */
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
	/**
	 * return the number of hour objects in the database
	 */
	public int getHoursCount(){
		return factory.getHourDAO().countHours();
	}
	/**
	 * @param id
	 * return a media type from the database given its id
	 */
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
	/**
	 * return the number of MediaTypes objects in the database
	 */
	public int getMediaTypesCount(){
		return factory.getMediaTypeDAO().countMediaTypes();
	}
	/**
	 * @param id
	 * return a room from the database given its id
	 */
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
	/**
	 * return all the rooms in the database
	 */
	public Collection<Room> getAllRooms(){
		return factory.getRoomDAO().findAll();
	}
	/**
	 * return the number of rooms in the database
	 */
	public int getRoomsCount(){
		return factory.getRoomDAO().countRooms();
	}
	/**
	 * @param id
	 * return a session from the database given its id
	 */
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
	/**
	 * return the number of sessions in the database
	 */
	public int getSessionsCount(){
		return factory.getSessionDAO().countSessions();
	}
	/**
	 * @param id
	 * return a subject from the database given its id
	 */
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
	/**
	 * @param id
	 * return a subject from the database given its code
	 */
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
	/**
	 * return the number of subjects in the database
	 */
	public int getSubjectsCount(){
		return factory.getSubjectDAO().countSubjects();
	}
	/**
	 * @param id
	 * return a teacher from the database given its id
	 */
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
	/**
	 * return the number of teachers in the database
	 */
	public int getTeachersCount(){
		return factory.getTeacherDAO().countTeachers();
	}
	/**
	 * deletes all the sessions from the database
	 */
	public void deleteAllSessions(){
		factory.getSessionDAO().deleteAllSessions();
	}
	/**
	 * inserts a session to the database
	 * @param s
	 */
	public void insertSession(Session s){
		factory.getSessionDAO().insertSession(s);
	}
	/**
	 * return the initial timetabling from the database
	 */
	public Collection<InitialTT> getInitialTT(){
		return factory.getInitialTTDAO().findAll();
	}
}
