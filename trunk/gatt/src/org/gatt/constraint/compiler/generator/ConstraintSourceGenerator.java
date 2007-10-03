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

public class ConstraintSourceGenerator {
	private ConstraintInfo cInfo;
	public static final String CLASS_NAME_PREFIX = "Constraint_";
	public static final String DEFAULT_OUTPUT_PACKAGE = "org.gatt.constraint.implemented";
	public static final String CONSTRAINT_PACKAGE = "org.gatt.constraint";
	public static final String DOMAIN_PACKAGE = "org.gatt.domain";
	
	private static final String NL = System.getProperties().getProperty("line.separator");
	
	public ConstraintSourceGenerator(ConstraintInfo cInfo){
		this.cInfo = cInfo;
	}
	public static String getGeneratedClassName(ConstraintInfo cInfo){	
		return DEFAULT_OUTPUT_PACKAGE + "." + CLASS_NAME_PREFIX + cInfo.getId();
	}	
	public static String getSimpleGeneratedClassName(ConstraintInfo cInfo){	
		return CLASS_NAME_PREFIX + cInfo.getId();
	}
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
		return buf.toString();
	}
	public static String generateStrategySourceCode(String constraint, Vector<Character> usedVars, ConstraintInfo cInfo){
		StringBuffer buf = new StringBuffer();
		for(char c : usedVars){
			buf.append("\t\tfor(int " +  c + " = 0; " +  c  + " < session.length; " + c + "++){" + NL);
			buf.append("\t\t\tif(session[" + c + "] == null) continue;");
			buf.append("\t\t\tif( !session[" + c + "].getGroup().applyConstraint(\"" + cInfo.getId() + "\")) continue;");	
		}
		buf.append(constraint);		
		for(char c : usedVars){
			buf.append("\t\t}" + NL);
		}
		buf.append("\t return " + CodifiableConstraintValue.ZERO.getJavaString() +";" + NL +"\t}");
		return buf.toString();
	}
	public static void main(String[] ar){
		StringComparableOperand compOperand1 = new StringComparableOperand("chucho", "Value1", "cosa1");
		StringComparableOperand compOperand2 = new StringComparableOperand("iValue2", "Value2", "cosa2");
		StringComparison co = new StringComparison(StringComparisonOperator.EQUALS, compOperand1, compOperand2);
		CompositeBooleanExpression cbe = new CompositeBooleanExpression(BooleanOperator.AND, ConstBooleanOperand.TRUE, co);
		CompositeBooleanExpression cbe2 = new CompositeBooleanExpression(BooleanOperator.OR, cbe, cbe);
		ConditionalConstraint conditional = new ConditionalConstraint(co, cbe, CodifiableConstraintValue.ONE);
		ConstraintInfo cInfo = new ConstraintInfo();
		cInfo.setId("myId");
		cInfo.setStrategyCodeImplementation(conditional.getJavaString());
		ConstraintSourceGenerator gen = new ConstraintSourceGenerator(cInfo);
		System.out.println(gen.getClassSourceCode());
	}
}
