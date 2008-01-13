package org.gatt.optimization.ttga;

import java.util.Vector;

import org.gatt.constraint.Constraint;
import org.gatt.constraint.ConstraintFacade;
import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.optimization.util.NumericTransformationFunction;
import org.gatt.optimization.util.SolutionManager;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class TTFitnessFunction extends FitnessFunction {
	
	private SolutionManager solutionManager;
	
	
	public TTFitnessFunction(int countRooms, int countHours){
		super();
		solutionManager = new SolutionManager(new NumericTransformationFunction(countRooms, countHours));
	}
	
	@Override
	protected double evaluate(IChromosome individual) {
		// TODO Auto-generated method stub
		Session[] sessions = solutionManager.transform(individual);
		ConstraintFacade constraintFacade = new ConstraintFacade();
		Vector<Constraint> constraints = constraintFacade.getCompiledConstraints();
		double val = 0;
		for(Constraint c : constraints)
			val = c.getSignificance() * c.evaluate(sessions).getValue();
		return 1 / val;
	}
	
	
	
}
