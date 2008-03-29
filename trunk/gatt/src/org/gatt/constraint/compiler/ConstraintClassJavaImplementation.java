package org.gatt.constraint.compiler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.SimpleJavaFileObject;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.compiler.generator.ConstraintSourceGenerator;

/**
 * represents a .java compilable file in memory. It contents the implementation source code ready to be compiled.
 * @author David
 *
 */
public class ConstraintClassJavaImplementation extends SimpleJavaFileObject {	
	
	/**
	 * Content of the java class file
	 */
	private String content;
	
	/** Constructor
	 * @param cInfo information of the constraint
	 * @param content content of the class file
	 * @throws URISyntaxException 
	 */
	public ConstraintClassJavaImplementation(ConstraintInfo cInfo, String content) throws URISyntaxException{
		super(new URI(ConstraintSourceGenerator.getGeneratedClassName(cInfo)), Kind.SOURCE);
		this.content = content;		
    }
	
	/* (non-Javadoc)
	 * @see javax.tools.SimpleJavaFileObject#getCharContent(boolean)
	 */
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    	return content;
    }
}