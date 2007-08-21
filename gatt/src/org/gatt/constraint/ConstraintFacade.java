package org.gatt.constraint;

import java.util.Iterator;

public class ConstraintFacade {
	private ConstraintManager manager;
	
	public ConstraintFacade(){
		manager = new ConstraintManager();
	}
	
	/*public void setXmlConstraintFilePath(String filePath){
		manager.setXmlConstraintFilePath(filePath);
	}*/
	
	public Iterator<Constraint> getCompiledConstraints(){
		return manager.getCompiledConstraints();
	}
	public static void main(String[] ar){
		ConstraintFacade f = new ConstraintFacade();
		Iterator<Constraint> it = f.getCompiledConstraints();
		if( it == null )
			System.out.println("DAMN :(");
		else
			System.out.println("Lol");
	}
}
