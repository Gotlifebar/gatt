package org.gatt.domain.factories.mysqldaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.gatt.domain.Subject;
import org.gatt.domain.factories.SubjectDAO;

public class MySqlSubjectDAO implements SubjectDAO {
	public int countSubjects() {
		Connection c = MySqlDAOFactory.getConnection();
		try{
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM Subjects");			
			ResultSet r = ps.executeQuery();			
			if( !r.next() )
				return -1;
			return r.getInt("total");			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	public Collection<Subject> findAll(){
		Connection c = MySqlDAOFactory.getConnection();
		Vector<Subject> subjects = new Vector<Subject>();
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Subjects");
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			while( r.next() ){
				Subject subject = new Subject();
				subject.setFac(r.getInt("fac"));				
				subject.setDep(r.getInt("dep"));				
				subject.setLetterCode(r.getString("letterCode"));				
				subject.setName(r.getString("name"));				
				subject.setCredits(r.getInt("credits"));				
				subject.setRestrictions(r.getString("restrictions"));				
				subject.setId(r.getInt("id"));				
				subjects.addElement(subject);
			}
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return subjects;
	}	

	public Subject findSubject(int id) {
		Connection c = MySqlDAOFactory.getConnection();
		Subject subject = null;
		//System.out.println("Finding subject: " + id);
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Subjects WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() )
				return null;
			//Was found... create
			subject = new Subject();
			
			subject.setFac(r.getInt("fac"));				
			subject.setDep(r.getInt("dep"));				
			subject.setLetterCode(r.getString("letterCode"));				
			subject.setName(r.getString("name"));				
			subject.setCredits(r.getInt("credits"));				
			subject.setRestrictions(r.getString("restrictions"));				
			subject.setId(r.getInt("id"));				
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return subject;
	}
	
	public Subject findSubjectByCode(int code){
		return findSubject(code);
	/*	Connection c = MySqlDAOFactory.getConnection();
		Subject s = null;
		try{			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM subjects WHERE code = ?");
			ps.setInt(1, code);
			ResultSet r = ps.executeQuery();
			//extract the elements from the ResultSet and create the object
			//If not found
			if( !r.next() ){
				System.out.println("No encontró SUBJECT (code): " + code);
				return null;
			}
			//Was found... create
			s = new Subject();
			s.setId(r.getInt("id"));
			s.setCode(r.getInt("code"));
			s.setName(r.getString("name"));
			s.setLetterCode(r.getString("letterCode"));
			s.setTheoricalHours(r.getInt("theoricalHours"));
			s.setPracticalHours(r.getInt("practicalHours"));
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return s;*/		
	}
}
