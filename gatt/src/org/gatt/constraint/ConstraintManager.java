package org.gatt.constraint;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Vector;

import org.gatt.constraint.compiler.ConstraintCompiler;
import org.gatt.constraint.compiler.generator.ConstraintSourceGenerator;
import org.gatt.constraint.io.XMLConstraintRepository;
import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;


/**
 * @author david
 * Class for managing constraints
 */
public class ConstraintManager {
	
	/**
	 * Constraint repository
	 */
	private XMLConstraintRepository repository;
	//private String xmlConstraintFilePath;
	/**
	 * Vector that contains all the compiled constraints
	 */
	private Vector<Constraint> compiledConstraints;
	
	/**
	 * Constructor
	 */
	public ConstraintManager(){
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);
		String xmlConstraintFilePath = null;
		try{
			xmlConstraintFilePath = config.getValue("XMLWriterInfo", "FilePath");
		}catch(JFigException jFigEx){
			jFigEx.printStackTrace();
		}
		
		/*URI uri = null;
		//String sFile = null;
		try{
			//sFile = config.getValue("XMLWriterInfo", "FilePath");
			//System.out.println(sFile);
			//uri = getClass().getResource(xmlConstraintFilePath).toURI();
		}catch(URISyntaxException uriEx){
			uriEx.printStackTrace();
		}*/
		repository = new XMLConstraintRepository(new File(xmlConstraintFilePath));
		//repository = new XMLConstraintRepository(new File(uri));		
	}	
	
	/**
	 * return all the compiled constraints
	 */
	public Vector<Constraint> getCompiledConstraints(){
		if( compiledConstraints == null )
			if( !compileConstraints() )
				return null;
		return compiledConstraints;
	}
	
	/**
	 * return true if it's necessary to compile the constraints, false otherwise.
	 */
	private boolean compileConstraints(){
		//Load all the constraints from the repository.
		if( !repository.load() )
			return false;		
		Iterator<ConstraintInfo> definedConstraints = repository.getAllConstraints().iterator();		
		ConstraintInfo cInfo;
		ConstraintCompiler compiler = new ConstraintCompiler();
		compiledConstraints = new Vector<Constraint>();
		while( definedConstraints.hasNext() ){
			
			cInfo = definedConstraints.next();
//System.out.println("Compiling: " + cInfo.getName());
			Constraint c = getCompiledConstraint(cInfo);
			if( c == null ){
				try {
					
					if( !compiler.compileConstraint(cInfo) )
						return false;
				}catch(URISyntaxException e){
					e.printStackTrace();
				}
			}
			compiledConstraints.add(getCompiledConstraint(cInfo));
		}
		return true;
	}
	/**
	 * @param cInfo
	 * return the compiled constraint corresponding the ConstraintInfo object passed as parameter
	 */
	private Constraint getCompiledConstraint(ConstraintInfo cInfo){
		String className = ConstraintSourceGenerator.getGeneratedClassName(cInfo);
		Constraint c = null;
		try{
			//Class cl = ;
			Constructor ctr = Class.forName(className).getConstructor(null);
			ctr.setAccessible(true);
			c = (Constraint)ctr.newInstance(null);						
		}catch(Exception e){
		//	e.printStackTrace();
			return null;
		}
		return c;
	}
/*	public void setXmlConstraintFilePath(String xmlConstraintFilePath) {
		this.xmlConstraintFilePath = xmlConstraintFilePath;
	}*/
}