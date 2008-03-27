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

/**
 * @author david
 * Produce constraint from information of the wizard
 */
public class ConstraintWizardProducer {
	
	/**
	 * list of used variables 
	 */
	private Vector<Character> usedVars;
	/**
	 * ConstraintInfo objects
	 */
	private ConstraintInfo cInfo;
	
	
	
	//private BooleanOperand constraint;
	/**
	 * constraint type
	 */
	private int constraintType;
	//private BooleanOperand _condition, _consequence;
	/**
	 * expressions
	 */
	private BooleanOperand[] expressions;
	/**
	 * Facade of codifiable constraints
	 */
	private ConstraintCodifiableFacade facade;
	private int current;
	public static final int CONDITION_EXPRESSION = 0,
							CONSEQUENCE_EXPRESSION = 1;
	
	
	
	/**
	 * constraint producer
	 */
	public ConstraintWizardProducer(){
		cInfo = new ConstraintInfo();
		facade = new ConstraintCodifiableFacade();
		current = CONDITION_EXPRESSION;
		expressions = new BooleanOperand[2];
		usedVars = new Vector<Character>();
		cInfo.setId(String.valueOf(System.currentTimeMillis()));
	}
	
	/**
	 * sets the constraint type
	 * @param type
	 */
	public void setConstraintType(int type){
		constraintType = type;
	}
	
	/**
	 * return the constraint type
	 */
	public int getConstraintType(){
		return constraintType;
	}
	
	
	/**
	 * sets the current expression
	 * @param expression
	 */
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
	
	/**
	 * return the current expression
	 */
	public int getCurrentExpression(){
		return current;
	}
	
	/**
	 * sets the constraint description
	 * @param description
	 */
	public void setConstraintDescription(String description) {
		cInfo.setDescription(description);
	}
	/**
	 * sets the constraint id
	 * @param id
	 */
	public void setConstraintId(String id) {
		cInfo.setId(id);
	}
	/**
	 * sets the constraint name
	 * @param name
	 */
	public void setConstraintName(String name) {
		cInfo.setName(name);
	}
	/**
	 * sets the constraint significance
	 * @param significance
	 */
	public void setConstraintSignificance(double significance) {
		cInfo.setSignificance(significance);
	}
	
	/**
	 * sets the constriant definition
	 * @param definition
	 */
	public void setConstraintDefinition(String definition){
		cInfo.setDefinition(definition);
	}
	
	/**
	 * sets the default comparison
	 * @param op
	 * @param op1
	 * @param op2
	 */
	public void setDefaultComparison(DefaultComparisonOperator op, ComparableOperand op1, ComparableOperand op2){
		//constraint = new DefaultComparison(op, op1, op2);		
		//current = facade.createDefaultComparison(op, op1, op2);
		expressions[current] = facade.createDefaultComparison(op, op1, op2);
	}
	
	/**
	 * sets the string comparison
	 * @param op
	 * @param op1
	 * @param op2
	 */
	public void setStringComparison(StringComparisonOperator op, StringComparableOperand op1, StringComparableOperand op2){
		//constraint = new StringComparison(op, op1, op2);		
		//current = facade.createStringComparison(op, op1, op2);
		expressions[current] = facade.createStringComparison(op, op1, op2);
	}
	
	/**
	 * create a comparison and returns a boolean operand
	 * @param op
	 * @param op1
	 * @param op2
	 * @param type
	 */
	private BooleanOperand createComparison(Operator op, ComparableOperand op1, ComparableOperand op2, Class type){
		if( type == String.class )			
			return facade.createStringComparison((StringComparisonOperator)op, (StringComparableOperand)op1, (StringComparableOperand)op2);
		else			
			return facade.createDefaultComparison((DefaultComparisonOperator)op, op1, op2);
	}
	
	/**
	 * sets a comparison
	 * @param op
	 * @param op1
	 * @param op2
	 * @param type
	 */
	public void setComparison(Operator op, ComparableOperand op1, ComparableOperand op2, Class type){
		expressions[current] = createComparison(op, op1, op2, type);
		/*if( type == String.class )			
			expressions[current] = facade.createStringComparison((StringComparisonOperator)op, (StringComparableOperand)op1, (StringComparableOperand)op2);
		else			
			expressions[current] = facade.createDefaultComparison((DefaultComparisonOperator)op, op1, op2);*/			
	}
	
	/**
	 * adds an AND comparison
	 * @param op
	 * @param op1
	 * @param op2
	 * @param type
	 */
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
	/**
	 * adds an OR comparison
	 * @param op
	 * @param op1
	 * @param op2
	 * @param type
	 */
	public void addORComparison(Operator op, ComparableOperand op1, ComparableOperand op2, Class type){
		expressions[current] = facade.addORComparison(expressions[current], createComparison(op, op1, op2, type));
		//expressions[current] = facade.addORComparison(expressions[current], comparison);
	}
	
	/**
	 * return the constraint preview
	 */
	public String getConstraintPreview(){		
		return facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getDisplayName();
	}
	
	/**
	 * return the produced constraint
	 */
	public ConstraintInfo getProducedConstraint(){		
		String strategy = ConstraintSourceGenerator.generateStrategySourceCode(facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getJavaString(), usedVars, cInfo);		
		cInfo.setStrategyCodeImplementation(strategy);
		cInfo.setDefinition(facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getReadableName());
		return cInfo;
	}
	
	/**
	 * return the constraint code
	 * @return
	 */
	public String getConstraintCode(){
		//
		return ConstraintSourceGenerator.generateStrategySourceCode(facade.createConstraint(constraintType, CodifiableConstraintValue.ONE, expressions).getJavaString(), usedVars, cInfo);
	}
	/**
	 * adds a used variable
	 * @param c
	 */
	public void addUsedVar(char c){
		if( usedVars.contains(c))
			return;
		usedVars.add(c);
	}
	/*public Vector<Character> getUsedVars(){
		return usedVars;
	}*/
}
