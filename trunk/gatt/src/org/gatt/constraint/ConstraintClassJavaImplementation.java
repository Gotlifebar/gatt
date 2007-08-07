package org.gatt.constraint;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.SimpleJavaFileObject;

public class ConstraintClassJavaImplementation extends SimpleJavaFileObject {
	
	private String content;
	
	public ConstraintClassJavaImplementation(String className, String content) throws URISyntaxException{
		super(new URI(className), Kind.SOURCE);
		this.content = content;		
    }
	
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    	return content;
    }
}