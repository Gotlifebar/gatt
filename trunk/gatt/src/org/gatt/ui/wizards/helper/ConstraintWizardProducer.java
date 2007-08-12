package org.gatt.ui.wizards.helper;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.codifiable.ConstraintCodifiableFacade;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.constraint.codifiable.stringexpression.StringComparableOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparison;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;

public class ConstraintWizardProducer {
	private ConstraintInfo cInfo;
	private BooleanOperand constraint;
	private ConstraintCodifiableFacade facade;
	
	public ConstraintWizardProducer(){
		cInfo = new ConstraintInfo();
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
	
	public void setDefaultComparison(DefaultComparisonOperator op, ComparableOperand op1, ComparableOperand op2){
		//constraint = new DefaultComparison(op, op1, op2);
		constraint = facade.createDefaultComparison(op, op1, op2);
	}
	
	public void setStringComparison(StringComparisonOperator op, StringComparableOperand op1, StringComparableOperand op2){
		//constraint = new StringComparison(op, op1, op2);
		constraint = facade.createStringComparison(op, op1, op2);
	}
	
	public void addANDComparison(BooleanOperand comparison){
		//constraint = new CompositeBooleanExpression(BooleanOperator.AND, constraint, comparison);
		constraint = facade.addANDComparison(constraint, comparison);
	}
	public void addORComparison(BooleanOperand comparison){
		//constraint = new CompositeBooleanExpression(BooleanOperator.OR, constraint, comparison);
		constraint = facade.addORComparison(constraint, comparison);
	}
	
	public String getConstraintPreview(){
		return constraint.getDisplayName();
	}
	
}
