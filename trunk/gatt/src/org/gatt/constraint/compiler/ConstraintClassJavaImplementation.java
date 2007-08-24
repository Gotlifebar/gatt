package org.gatt.constraint.compiler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.SimpleJavaFileObject;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.compiler.generator.ConstraintSourceGenerator;

public class ConstraintClassJavaImplementation extends SimpleJavaFileObject {	
	
	private String content;
	
	public ConstraintClassJavaImplementation(ConstraintInfo cInfo, String content) throws URISyntaxException{
		super(new URI(ConstraintSourceGenerator.getGeneratedClassName(cInfo)), Kind.SOURCE);
		this.content = content;		
    }
	
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    	return content;
    }
}