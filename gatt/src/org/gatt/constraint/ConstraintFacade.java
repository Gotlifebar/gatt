package org.gatt.constraint;

import java.util.Iterator;

public class ConstraintFacade {
	private ConstraintManager manager;
	
	public ConstraintFacade(){
		manager = new ConstraintManager();
	}
	
	public void setXmlConstraintFilePath(String filePath){
		manager.setXmlConstraintFilePath(filePath);
	}
	
	public Iterator<Constraint> getCompiledConstraints(){
		return manager.getCompiledConstraints();
	}	
}
