package org.gatt.constraint.compiler.generator;

import java.util.Vector;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.codifiable.CodifiableConstraintValue;
import org.gatt.constraint.codifiable.ConditionalConstraint;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.ConstBooleanOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparableOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparison;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;

/**
 * This class generates the code of a constraint from a ConstraintInfo element. 
 * The class acts as a java code generator template similar to the generator classes produced by JET 1.0
 * @author David
 *
 */
public class ConstraintSourceGenerator {
	
	/**
	 * information of the constraint
	 */
	private ConstraintInfo cInfo;
	
	/**
	 * Prefix for the constraint class names
	 */
	public static final String CLASS_NAME_PREFIX = "Constraint_";
	
	/**
	 * Package where the constraints will be located after the compilation
	 */
	public static final String DEFAULT_OUTPUT_PACKAGE = "org.gatt.constraint.implemented";
	
	/**
	 * Package with the constraint base classes to be imported
	 */
	public static final String CONSTRAINT_PACKAGE = "org.gatt.constraint";
	
	/**
	 * Package with the domain base classes to be imported
	 */
	public static final String DOMAIN_PACKAGE = "org.gatt.domain";
	
	/**
	 * Represents a simple new Line.
	 */
	private static final String NL = System.getProperties().getProperty("line.separator");
	
	/** Constructor
	 * @param cInfo information of the constraint to be generated
	 */
	public ConstraintSourceGenerator(ConstraintInfo cInfo){
		this.cInfo = cInfo;
	}
	
	/** Returns the class name of the constraint to be generated
	 * @param cInfo information of the constraint
	 * @return the class name of the constraint to be generated
	 */
	public static String getGeneratedClassName(ConstraintInfo cInfo){	
		return DEFAULT_OUTPUT_PACKAGE + "." + CLASS_NAME_PREFIX + cInfo.getId();
	}	
	
	/** Returns the simple (without the package prefix) class name of a constraint 
	 * @param cInfo information of the constraint
	 * @return the simple class name of a constraint
	 */
	public static String getSimpleGeneratedClassName(ConstraintInfo cInfo){	
		return CLASS_NAME_PREFIX + cInfo.getId();
	}
	
	/** Returns the source code of the class for the constraint
	 * @return the source code of the class for the constraint
	 */
	public String getClassSourceCode(){
		StringBuffer buf = new StringBuffer();
		buf.append("package " + DEFAULT_OUTPUT_PACKAGE + ";" + NL + NL);
		buf.append("import " + DOMAIN_PACKAGE + ".*;" + NL);
		buf.append("import " + CONSTRAINT_PACKAGE + ".*;" + NL + NL );
		buf.append("class " + getSimpleGeneratedClassName(cInfo) + " implements Constraint{" + NL);
		buf.append("\tpublic " + getSimpleGeneratedClassName(cInfo) + "(){}");
		buf.append("\tpublic double getSignificance(){" + NL + "\t\treturn " + cInfo.getSignificance() + ";" + NL + "\t}"); 
		buf.append("\tpublic ConstraintValue evaluate(Session[] session){" + NL );
		buf.append(cInfo.getStrategyCodeImplementation());
		//buf.append(getStrategySourceCode());
		buf.append(NL + "}");
		System.out.println("SOURCE CODE: " + buf.toString());
		return buf.toString();
	}
	/** Returns the source code of the method "evaluate" of a constraint
	 * @param constraint constraint source code
	 * @param usedVars variables used in the iterations of the cycle over the sessions array
	 * @param cInfo information of the constraint
	 * @return the source code of the method "evaluate" of a constraint
	 */
	public static String generateStrategySourceCode(String constraint, Vector<Character> usedVars, ConstraintInfo cInfo){
		StringBuffer buf = new StringBuffer();
		for(char c : usedVars){
			buf.append("int cont = 0;" + NL);
			buf.append("int total = 0;" + NL);
			buf.append("\t\tfor(int " +  c + " = 0; " +  c  + " < session.length; " + c + "++){" + NL);
			buf.append("\t\t\tif(session[" + c + "] == null) continue;");
			buf.append("total++;" + NL);
			buf.append("\t\t\tif( !session[" + c + "].getGroup().applyConstraint(\"" + cInfo.getId() + "\")) continue;");	
		}
		buf.append(constraint);		
		for(char c : usedVars){
			buf.append("\t\t}" + NL);
		}
		//buf.append("\t return " + CodifiableConstraintValue.ZERO.getJavaString() +";" + NL +"\t}");
		buf.append("\t return new ConstraintValue((float)cont / (float)total);" + NL +"\t}");
		return buf.toString();
	}
	
}
