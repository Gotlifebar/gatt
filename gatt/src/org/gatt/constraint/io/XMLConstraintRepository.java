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



/**
 * This class represents a parsed xml file that contains one or more constraints. 
 * It loads an xml file containing constraints and provides services to access the information of those constraints.
 * @author David
 *
 */
public class XMLConstraintRepository{
	private File file;
	private HashMap<String, ConstraintInfo> constraints;
	
	/** Constructor of the repository from a path
	 * @param fileName path of the xml file
	 */
	public XMLConstraintRepository(String fileName){
		this.file = new File(fileName);
		constraints = null;
    }
	
	/** Constructor of the repository from a File object
	 * @param file file object of the repository
	 */
	public XMLConstraintRepository(File file){
		this.file = file;
		constraints = null;
	}
	
	/** Load the repository
	 * @return true if the repository is successfully loaded, false otherwise
	 */
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
	
	/** Returns the constraints in the repository
	 * @return the constraints in the repository
	 */
	public Collection<ConstraintInfo> getAllConstraints(){
		return constraints.values();
//		constraints.
//		return constraints.elementAt(index);
	}
	
	/** Finds a constraint given its id
	 * @param id constraint id
	 * @return the constraint corresponding to the id
	 */
	public ConstraintInfo getConstraintById(String id){
		return constraints.get(id);
	}
	
	/** Sets the file path where the repository is located
	 * @param fileName file path where the repository is located
	 */
	public void setFileName(String fileName){
		this.file = new File(fileName);
		constraints = null;
	}
	
	/** Returns if the repository is already loaded
	 * @return true if the repository is already loaded, false otherwise
	 */
	public boolean isLoaded(){
		return constraints == null;
	}
}