package org.gatt.constraint.compiler.generator;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.ConstraintValue;
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
	public static final String DEFAULT_OUTPUT_PACKAGE = "org.gatt.constraint.generated";
	public static final String CONSTRAINT_PACKAGE = "org.gatt.constraint.*";
	public static final String DOMAIN_PACKAGE = "org.gatt.domain.*";
	
	private final String NL = System.getProperties().getProperty("line.separator");
	
	public ConstraintSourceGenerator(ConstraintInfo cInfo){
		this.cInfo = cInfo;
	}
	public String generate(){
		StringBuffer buf = new StringBuffer();
		buf.append("package " + DEFAULT_OUTPUT_PACKAGE + ";" + NL + NL);
		buf.append("import " + DOMAIN_PACKAGE + ";" + NL);
		buf.append("import " + CONSTRAINT_PACKAGE + ";" + NL + NL );
		buf.append("public class Constraint_" + cInfo.getId() + " implements Constraint{" + NL);
		buf.append("\tpublic ConstraintValue evaluate(Session[] session){" + NL );
		buf.append("\t\tfor(int i = 0; i < session.length; i++){" + NL);
		buf.append(cInfo.getStrategyCodeImplementation());
		buf.append("\t}" + NL + "}");
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
		System.out.println(gen.generate());
	}
}
