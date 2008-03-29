package org.gatt.constraint.codifiable;

import org.gatt.constraint.codifiable.boolexpression.BooleanOperand;
import org.gatt.constraint.codifiable.boolexpression.BooleanOperator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.constraint.codifiable.boolexpression.CompositeBooleanExpression;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparison;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.constraint.codifiable.stringexpression.StringComparableOperand;
import org.gatt.constraint.codifiable.stringexpression.StringComparison;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;

/**
 * This façade acts like a factory and allows the higher level classes to manipulate the creation of Comparisons, 
 * Operands and Constraints, seeking the code generation of these elements. 
 * The façade has methods to compose and create comparisons.
 * @author David
 *
 */
public class ConstraintCodifiableFacade {
	
	/**
	 * Constraint types
	 */
	public static final int CONDITIONAL_CONSTRAINT = 1,
							SIMPLE_CONSTRAINT = 2;
							
	
	/**
	 * Void constructor
	 */
	public ConstraintCodifiableFacade(){
		
	}
	/** Creates a default comparison
	 * @param op comparison operator
	 * @param op1 left hand operand
	 * @param op2 right hand operand
	 * @return the created default comparison
	 */
	public DefaultComparison createDefaultComparison(DefaultComparisonOperator op, ComparableOperand op1, ComparableOperand op2){
		return new DefaultComparison(op, op1, op2);
	}
	/** Creates a String comparison
	 * @param op String comparison operator
	 * @param op1 left hand operand
	 * @param op2 right hand operand
	 * @return the created string comparison
	 */
	public StringComparison createStringComparison(StringComparisonOperator op, StringComparableOperand op1, StringComparableOperand op2){
		return new StringComparison(op, op1, op2);
	}
	
	/** Creates an operand
	 * @param javaString String in java language representing the operand in code 
	 * @param name name of the operand
	 * @param readableName readableName of the operand
	 * @param type operand type
	 * @return the new created operand
	 */
	public ComparableOperand createOperand(String javaString, String name, String readableName, Class type){
		if( type == String.class)
			return new StringComparableOperand(javaString, name, readableName);
		return new BooleanOperand(javaString, name, readableName);		
	}
	
	/** Adds a comparison concatenated with the AND operator to an operand
	 * @param operand operand to be extended
	 * @param comparison comparison to be added
	 * @return the operand concatenated with the AND operator with the comparison
	 */
	public BooleanOperand addANDComparison(BooleanOperand operand, BooleanOperand comparison){
		return (new CompositeBooleanExpression(BooleanOperator.AND, operand, comparison));		
	}
	/** Adds a comparison concatenated with the OR operator to an operand
	 * @param operand operand to be extended
	 * @param comparison comparison to be added
	 * @return the operand concatenated with the OR operator with the comparison
	 */
	public BooleanOperand addORComparison(BooleanOperand operand, BooleanOperand comparison){
		return (new CompositeBooleanExpression(BooleanOperator.OR, operand, comparison));		
	}
	
	/** Creates a constraint
	 * @param type constraint type
	 * @param penalty constraint penalty
	 * @param params operands to create the constraint, one if is a simple constraint and two if is a conditional constraint
	 * @return the new created constraint
	 */
	public CodifiableDisplayableObject createConstraint(int type, CodifiableConstraintValue penalty, BooleanOperand ... params){
		CodifiableDisplayableObject constraint = null;
		switch(type){
			case SIMPLE_CONSTRAINT:
				constraint = new SimpleConstraint(params[0], penalty);
				break;
			case CONDITIONAL_CONSTRAINT:
				constraint = new ConditionalConstraint(params[0], params[1], penalty);
				break;
		}
		return constraint;
	}
}
