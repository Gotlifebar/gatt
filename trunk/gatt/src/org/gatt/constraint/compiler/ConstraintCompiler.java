package org.gatt.constraint.compiler;

import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.compiler.generator.ConstraintSourceGenerator;

public class ConstraintCompiler {
	private static ConstraintCompiler instance = null;
	public static ConstraintCompiler getInstance(){
		if( instance == null)
			instance = new ConstraintCompiler();
		return instance;
	}
	public boolean compileConstraint(ConstraintInfo cInfo) throws URISyntaxException{
		//Get the compiler Tool
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//Construct a diagnostic Listener
		DiagnosticListener<JavaFileObject> diagnosticsCollector = new ConstraintDiagnosticListener();
		//Get a File Manager
		StandardJavaFileManager fileManager  = compiler.getStandardFileManager(diagnosticsCollector, null, null);//compiler.getStandardFileManager(diagnosticsCollector);
		//Generate the code for the class
		JavaFileObject javaObjectFromString = getConstraintJavaImplementation(cInfo);
		//Create a list of the files to compile
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(javaObjectFromString);
		//Create a compilation Task
		CompilationTask task = compiler.getTask(null, fileManager, diagnosticsCollector, null, null, fileObjects);
		//Execute the compilation task and return the result.
		return task.call();
	}
	
	public ConstraintClassJavaImplementation getConstraintJavaImplementation(ConstraintInfo cInfo) throws URISyntaxException{
		//Generate the .java virtual file from the cInfo.
		ConstraintSourceGenerator gen = new ConstraintSourceGenerator(cInfo);		
		return new ConstraintClassJavaImplementation(ConstraintSourceGenerator.getGeneratedClassName(cInfo), gen.getClassSourceCode());
	}
	
	//This class is a listener of the problems.
	private static class ConstraintDiagnosticListener implements DiagnosticListener {
		public void report(Diagnostic message) {
			StringBuffer messageString = new StringBuffer()
			.append("Kind: ").append(message.getKind()).append('\n')
			.append("Position: ").append(message.getPosition()).append('\n')
			.append("Start Position: ").append(message.getStartPosition()).append('\n')
			.append("End Position: ").append(message.getEndPosition()).append('\n')
			.append("Source: ").append(message.getSource());			
			System.out.println(messageString);			
		}
	}
}
