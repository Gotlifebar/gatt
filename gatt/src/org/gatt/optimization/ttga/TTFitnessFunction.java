package org.gatt.optimization.ttga;

import java.util.Vector;

import org.gatt.constraint.Constraint;
import org.gatt.constraint.ConstraintFacade;
import org.gatt.domain.Session;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class TTFitnessFunction extends FitnessFunction {

	@Override
	protected double evaluate(IChromosome individual) {
		// TODO Auto-generated method stub
		Session[] sessions = transform(individual);
		ConstraintFacade constraintFacade = new ConstraintFacade();
		Vector<Constraint> constraints = constraintFacade.getCompiledConstraints();
		double val = 0;
		for(Constraint c : constraints)
			val = c.getSignificance() * c.evaluate(sessions).getValue();
		
		return 1 / val;
	}
	
	private Session[] transform(IChromosome individual){
		//TODO .... make the transformation.
		return null;
	}
	
}
