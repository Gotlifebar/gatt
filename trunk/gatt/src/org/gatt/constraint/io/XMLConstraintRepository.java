package org.gatt.constraint.io;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.gatt.constraint.ConstraintInfo;
import org.xml.sax.SAXException;


//Represents a processed XML File that contains one or more Constraints
public class XMLConstraintRepository{
	private File file;
	private HashMap<String, ConstraintInfo> constraints;
	
	public XMLConstraintRepository(String fileName){
		this.file = new File(fileName);
		constraints = null;
    }
	
	public boolean load(){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//factory.setValidating(true); //This is for validate the xml structure with a DTD
			SAXParser parser = factory.newSAXParser();
			ConstraintRepositoryContentHandler crch = new ConstraintRepositoryContentHandler();
			
			parser.parse(file, crch);
			constraints = crch.getConstraints();			
		}catch (ParserConfigurationException e){
			System.err.println(e.toString());
			return false;
		} catch (SAXException e){
			System.err.println(e.toString());
			return false;
		} catch (IOException e){
			System.err.println(e.toString());
			return false;
		}
		return true;
	}
	
	public Collection<ConstraintInfo> getAllConstraints(){
		return constraints.values();
//		constraints.
//		return constraints.elementAt(index);
	}
	/*public ConstraintInfo getConstraintById(String id){
		return constraints.get(id);
		for(ConstraintInfo c : constraints)
			if( c.getId().compareTo(id) == 0)
				return c;
		return null;
	}*/
	
	public void setFileName(String fileName){
		this.file = new File(fileName);
		constraints = null;
	}
	
	public boolean isLoaded(){
		return constraints == null;
	}
}