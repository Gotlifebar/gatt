package org.gatt.constraint;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Vector;

import org.gatt.constraint.compiler.ConstraintCompiler;
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
	
	private boolean compileConstraints(){
		//Load all the constraints from the repository.
		if( !repository.load() )
			return false;		
		Iterator<ConstraintInfo> definedConstraints = repository.getAllConstraints().iterator();
		ConstraintInfo cInfo;
		ConstraintCompiler compiler = new ConstraintCompiler();
		while( definedConstraints.hasNext() ){
			cInfo = definedConstraints.next();
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