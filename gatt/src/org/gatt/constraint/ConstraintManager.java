package org.gatt.constraint;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.gatt.constraint.compiler.generator.ConstraintSourceGenerator;
import org.gatt.constraint.io.XMLConstraintRepository;


public class ConstraintManager {
	
	private XMLConstraintRepository repository;
	private String xmlConstraintFileName;
	private Vector<Constraint> compiledConstraints;
	
	public ConstraintManager(){
		repository = new XMLConstraintRepository(xmlConstraintFileName);
	}
	
	public boolean compileConstraint(String id){
		
		return true;
	}
	
	public Iterator<Constraint> getCompiledConstraints(){
		if( compiledConstraints == null )
			compileConstraints();
		return compiledConstraints.iterator();
	}
	
	private void compileConstraints(){
		//Load all the constraints from the repository.
		if( !repository.load() )
			return;		
		Iterator<ConstraintInfo> definedConstraints = repository.getAllConstraints().iterator();
		
		//First check if it's already compiled.
		
		
	}
	private Constraint getCompiledConstraint(ConstraintInfo cInfo){
		String className = ConstraintSourceGenerator.getGeneratedClassName(cInfo);
		Constraint c = null;
		try{
			c = (Constraint)Class.forName(className).newInstance();			
		}catch(Exception e){
			return null;
		}
		return c;		
	}
	public void setXmlConstraintFileName(String xmlConstraintFileName) {
		this.xmlConstraintFileName = xmlConstraintFileName;
	}
}
/*

package test;
import java.net.URI;

class JavaObjectFromString extends SimpleJavaFileObject{
    private String contents = null;
    
	public JavaObjectFromString(String className, String contents) throws Exception{
    	super(new URI(className), Kind.SOURCE);
		this.contents = contents;
    }
    
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    		return contents;
    }
}

*/