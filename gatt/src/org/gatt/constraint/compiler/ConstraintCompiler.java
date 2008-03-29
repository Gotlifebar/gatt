package org.gatt.constraint.compiler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.compiler.generator.ConstraintSourceGenerator;
import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;

/**
 * This class is the compiler of contraints; it creates the .class files from a ConstraintInfo element. 
 * The compiler uses the JavaCompiler defined in the javax.tools package.
 * @author David
 *
 */
public class ConstraintCompiler {
	
//	private static final String CONSTRAINT_OUTPUT_PATH = "bin/";
	
	/**
	 * Configuration instance
	 */
	private JFigIF config;
	
	/**
	 * Compiler instance
	 */
	private static ConstraintCompiler instance = null;
	
	/** Singleton method implementation 
	 * @return the compiler instance
	 */
	public static ConstraintCompiler getInstance(){
		if( instance == null)
			instance = new ConstraintCompiler();
		return instance;
	}
	
	/**
	 * Constructor of the compiler
	 */
	public ConstraintCompiler(){
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		config = JFig.getInstance(locator);
	}
	
	/**
	 * compiles a constraint given its information
	 * @param cInfo information of the constraint
	 * @return true if the compilation process is successfull, false otherwise
	 * @throws URISyntaxException
	 */
	public boolean compileConstraint(ConstraintInfo cInfo) throws URISyntaxException{
		//Get the compiler Tool
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//Construct a diagnostic Listener
		DiagnosticListener<JavaFileObject> diagnosticsCollector = new ConstraintDiagnosticListener();
		//Get a File Manager
		StandardJavaFileManager fileManager  = compiler.getStandardFileManager(diagnosticsCollector, null, null);
		//Set the class output of the file manager
		try {
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, 
									Collections.singleton(new File(config.getValue("ConstraintCompilerOptions", "OutputDir"))));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JFigException e) {
			e.printStackTrace();
		}
			
		//System.out.println("Lol, compiling ....");
		//StandardLocation s;
		//fileManager.getFileForInput(arg0, arg1, arg2)
		//if( !writeTempFileFor(cInfo) )
		//	return false;		
		//Generate the code for the class
		JavaFileObject javaFileSource = getConstraintJavaImplementation(cInfo);//null;		
try {
	System.out.println(javaFileSource.getCharContent(true));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		//Create a list of the files to compile
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(javaFileSource);
		//Create a compilation Task
		CompilationTask task = compiler.getTask(new PrintWriter(System.out), 
												fileManager, 
												diagnosticsCollector, 
												null, 
												null, 
												fileObjects);
		//Execute the compilation task and return the result.
		boolean exec = task.call();
		
		//deleteTempFile(cInfo);
		return exec;
	}
	
	public ConstraintClassJavaImplementation getConstraintJavaImplementation(ConstraintInfo cInfo)
		throws URISyntaxException{
		//Generate the .java virtual file from the cInfo.
		ConstraintSourceGenerator gen = new ConstraintSourceGenerator(cInfo);
//System.out.println(gen.getClassSourceCode());
		return new ConstraintClassJavaImplementation(cInfo, gen.getClassSourceCode());
	}
	
	/*public static String getPathFor(ConstraintInfo cInfo){
		return CONSTRAINT_OUTPUT_PATH + getPathFromPackage(ConstraintSourceGenerator.getGeneratedClassName(cInfo)) + ".java";
	}
	public static String getPathFromPackage(String className){
		return className.replace('.', '/');
	}*/
	
	/*private boolean writeTempFileFor(ConstraintInfo cInfo){
		try{
			File f = new File(getPathFor(cInfo));			
			System.out.println("Creando: " + f.getAbsolutePath());
			//f.createNewFile();
			PrintWriter writer = new PrintWriter( new FileWriter( f ));
			
			ConstraintSourceGenerator gen = new ConstraintSourceGenerator(cInfo);
			String sourceCode = gen.getClassSourceCode();
			writer.println( sourceCode );
			writer.close();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	private boolean deleteTempFile(ConstraintInfo cInfo){
		File f = new File(getPathFor(cInfo));		
		return f.delete();
	}*/
	
	//This class is a listener of the problems.
	/**
	 * This class observes the compilation process
	 * @author David
	 *
	 */
	private static class ConstraintDiagnosticListener implements DiagnosticListener {
		
		/* (non-Javadoc)
		 * @see javax.tools.DiagnosticListener#report(javax.tools.Diagnostic)
		 */
		public void report(Diagnostic message) {
			StringBuffer messageString = new StringBuffer()
			.append("Kind: ").append(message.getKind()).append('\n')
			.append("Position: ").append(message.getPosition()).append('\n')
			.append("Start Position: ").append(message.getStartPosition()).append('\n')
			.append("End Position: ").append(message.getEndPosition()).append('\n')
			.append("Source: ").append(message.getSource()).append('\n')
			.append("Code: ").append(message.getCode())
			.append("Message: ").append(message.getMessage(null));			
			System.out.println(messageString);
		}
	}
}
