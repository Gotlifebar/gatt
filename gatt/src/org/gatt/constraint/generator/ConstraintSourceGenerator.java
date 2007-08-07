package org.gatt.constraint.generator;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.ConstraintValue;

public class ConstraintSourceGenerator {
	private ConstraintInfo cInfo;
	public static final String DEFAULT_OUTPUT_PACKAGE = "constraint.generated";
	public static final String CONSTRAINT_PACKAGE = "constraint.*";
	
	private final String NL = System.getProperties().getProperty("line.separator");
	
	public ConstraintSourceGenerator(ConstraintInfo cInfo){
		this.cInfo = cInfo;
	}
	public String generate(){
		StringBuffer buf = new StringBuffer();
		buf.append("package " + DEFAULT_OUTPUT_PACKAGE + ";" + NL + NL);
		buf.append("import " + CONSTRAINT_PACKAGE + ";" + NL + NL );
		buf.append("public class Constraint_" + cInfo.getId() + " implements Constraint{" + NL);
		buf.append("\tpublic ConstraintValue evaluate(Object context){" + NL );		
		return buf.toString();
	}
}
