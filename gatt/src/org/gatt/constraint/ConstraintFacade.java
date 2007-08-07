package org.gatt.constraint;

import org.gatt.constraint.io.XMLConstraintRepository;


public class ConstraintFacade {
	
	private XMLConstraintRepository repository;
	
	public boolean compileConstraint(String id){

		return true;
	}
	public Constraint getConstraint(String id){
		return null;
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