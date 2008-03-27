package org.gatt.constraint;

import org.gatt.domain.Session;

/**
 * @author David
 * Constraint interface
 */
public interface Constraint {
	
	/**
	 * Evaluates a constraint which is an array of sessions
	 * @param sessions array of sessions
	 * @return ConstraintValue object containing evaluation result
	 */
	public ConstraintValue evaluate(Session[] sessions);
	
	/**
	 * return the significance of the constraint
	 * @return double value with the significance of the constraint
	 */
	public double getSignificance();
}
