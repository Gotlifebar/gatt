package org.gatt.constraint;

import org.gatt.domain.Session;

public interface Constraint {
	public ConstraintValue evaluate(Session[] sessions);
}
