package org.gatt.ui.wizards.helper;

import java.util.Vector;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.codifiable.CodifiableConstraintValue;
import org.gatt.constraint.codifiable.ConstraintCodifiableFacade;
import org.gatt.constraint.codifiable.Operator;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.constraint.codifiable.stringexpression.StringComparableOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;
import org.gatt.constraint.compiler.generator.ConstraintSourceGenerator;

public class ConstraintWizardProducer {
	private Vector<Character> usedVars;
	private ConstraintInfo cInfo;
	
	
	
	//private BooleanOperand constraint;
	private int constraintType;
	//private BooleanOperand _condition, _consequence;
	private BooleanOperand[] expressions;
	private ConstraintCodifiableFacade facade;
	private int current;
	public static final int CONDITION_EXPRESSION = 0,
							CONSEQUENCE_EXPRESSION = 1;
	
	
	
	public ConstraintWizardProducer(){
		cInfo = new ConstraintInfo();
		facade = new ConstraintCodifiableFacade();
		current = CONDITION_EXPRESSION;
		expressions = new BooleanOperand[2];
		usedVars = new Vector<Character>();
		cInfo.setId(String.valueOf(System.currentTimeMillis()));
	}
	
	public void setConstraintType(int type){
		constraintType = type;
	}
	
	public int getConstraintType(){
		return constraintType;
	}
	
	
	public void setCurrentExpression(int expression){
		current = expression;
		/*switch(expression){
			case CONDITION_EXPRESSION:
				current = _condition;
				break;
			case CONSEQUENCE_EXPRESSION:
				current = _consequence;
		}*/
	}
	
	public int getCurrentExpression(){
		return current;
	}
	
	public void setConstraintDescription(String description) {
		cInfo.setDescription(description);
	}
	public void setConstraintId(String id) {
		cInfo.setId(id);
	}
	public void setConstraintName(String name) {
		cInfo.setName(name);
	}
	public void setConstraintSignificance(double significance) {
		cInfo.setSignificance(significance);
	}
	
	public void setConstraintDefinition(String definition){
		cInfo.setDefinition(definition);
	}
	
	public void setDefaultComparison(DefaultComparisonOperator op, ComparableOperand op1, ComparableOperand op2){
		//constraint = new DefaultComparison(op, op1, op2);		
		//current = facade.createDefaultComparison(op, op1, op2);
		expressions[current] = facade.createDefaultComparison(op, op1, op2);
	}
	
	public void setStringComparison(StringComparisonOperator op, StringComparableOperand op1, StringComparableOperand op2){
		//constraint = new StringComparison(op, op1, op2);		
		//current = facade.createStringComparison(op, op1, op2);
		expressions[current] = facade.createStringComparison(op, op1, op2);
	}
	
	private BooleanOperand createComparison(Operator op, ComparableOperand op1, ComparableOperand op2, Class type){
		if( type == String.class )			
			return facade.createStringComparison((StringComparisonOperator)op, (StringComparableOperand)op1, (StringComparableOperand)op2);
		else			
			return facade.createDefaultComparison((DefaultComparisonOperator)op, op1, op2);
	}
	
	public void setComparison(Operator op, ComparableOperand op1, ComparableOperand op2, Class type){
		expressions[current] = createComparison(op, op1, op2, type);
		/*if( type == String.class )			
			expressions[current] = facade.createStringComparison((StringComparisonOperator)op, (StringComparableOperand)op1, (StringComparableOperand)op2);
		else			
			expressions[current] = facade.createDefaultComparison((DefaultComparisonOperator)op, op1, op2);*/			
	}
	
	public void addANDComparison(Operator op, ComparableOperand op1, ComparableOperand op2, Class type){
		expressions[current] = facade.addANDComparison(expressions[current], createComparison(op, op1, op2, type));
		//expressions[current] = facade.addANDComparison(expressions[current], comparison);
	}
	/*public void addANDComparison(BooleanOperand comparison){
		//constraint = new CompositeBooleanExpression(BooleanOperator.AND, constraint, comparison);
		expressions[current] = facade.addANDComparison(expressions[current], comparison);
	}*/
	/*public void addORComparison(BooleanOperand comparison){
		//constraint = new CompositeBooleanExpression(BooleanOperator.OR, constraint, comparison);		
		expressions[current] = facade.addORComparison(expressions[current], comparison);
	}*/
	public void addORComparison(Operator op, ComparableOperand op1, ComparableOperand op2, Class type){
		expressions[current] = facade.addORComparison(expressions[current], createComparison(op, op1, op2, type));
		//expressions[current] = facade.addORComparison(expressions[current], comparison);
	}
	
	public String getConstraintPreview(){		
		return facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getDisplayName();
	}
	
	public ConstraintInfo getProducedConstraint(){		
		String strategy = ConstraintSourceGenerator.generateStrategySourceCode(facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getJavaString(), usedVars, cInfo);		
		cInfo.setStrategyCodeImplementation(strategy);
		cInfo.setDefinition(facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getReadableName());
		return cInfo;
	}
	
	public String getConstraintCode(){
		//
		return ConstraintSourceGenerator.generateStrategySourceCode(facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getJavaString(), usedVars, cInfo);
	}
	public void addUsedVar(char c){
		if( usedVars.contains(c))
			return;
		usedVars.add(c);
	}
	/*public Vector<Character> getUsedVars(){
		return usedVars;
	}*/
}
