package org.gatt.constraint;

import java.util.Vector;

public class ConstraintFacade {
	private ConstraintManager manager;
	
	public ConstraintFacade(){
		manager = new ConstraintManager();
	}
	
	/*public void setXmlConstraintFilePath(String filePath){
		manager.setXmlConstraintFilePath(filePath);
	}*/
	
	public Vector<Constraint> getCompiledConstraints(){
		return manager.getCompiledConstraints();
	}
	public static void main(String[] ar){
		ConstraintFacade f = new ConstraintFacade();
		Iterable<Constraint> it = f.getCompiledConstraints();
		for(Constraint c : it){
			if( c == null ){
				System.out.println("DAMN :(");
				continue;
			}
			c.evaluate(null);
		}
		/*if( it == null )
			System.out.println("DAMN :(");
		else
			System.out.println("Lol");*/
	}
}
