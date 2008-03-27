package org.gatt.constraint;

import java.util.Vector;

/**
 * @author David
 * Facade that provides services for managing constraints
 */
public class ConstraintFacade {
	
	/**
	 * the constraint manager 
	 */
	private ConstraintManager manager;
	
	/**
	 * Constructor 
	 */
	public ConstraintFacade(){
		manager = new ConstraintManager();
	}
	
	/*public void setXmlConstraintFilePath(String filePath){
		manager.setXmlConstraintFilePath(filePath);
	}*/
	
	/**
	 * Return a vector with the compiled constraints
	 */
	public Vector<Constraint> getCompiledConstraints(){
		return manager.getCompiledConstraints();
	}
	
	/*public static void main(String[] ar){
		ConstraintFacade f = new ConstraintFacade();
		Iterable<Constraint> it = f.getCompiledConstraints();
		for(Constraint c : it){
			if( c == null ){
				System.out.println("DAMN :(");
				continue;
			}
			c.evaluate(null);
			System.out.println("Lol: " + c.getSignificance());
		}
		/*if( it == null )
			System.out.println("DAMN :(");
		else
			System.out.println("Lol");
	}*/
}
