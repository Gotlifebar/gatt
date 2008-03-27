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

/**
 * @author Chucho
 * Timetabling fitness function
 */
public class TTFitnessFunction extends FitnessFunction {
	
	/**
	 * A solution manager
	 */
	private SolutionManager solutionManager;
	
	
	/**
	 * constructor
	 * @param countRooms
	 * @param countHours
	 */
	public TTFitnessFunction(int countRooms, int countHours){
		super();
		solutionManager = new SolutionManager(new NumericTransformationFunction(countRooms, countHours));
	}
	
	/* (non-Javadoc)
	 * @see org.jgap.FitnessFunction#evaluate(org.jgap.IChromosome)
	 */
	@Override
	protected double evaluate(IChromosome individual) {
		// TODO Auto-generated method stub
		Session[] sessions = solutionManager.transform(individual);
		ConstraintFacade constraintFacade = new ConstraintFacade();
		Vector<Constraint> constraints = constraintFacade.getCompiledConstraints();
		double val = 0;
		long timeInit  = System.currentTimeMillis();
		for(Constraint c : constraints){

//			long timeInit  = System.currentTimeMillis();
			double eval = c.evaluate(sessions).getValue();
//			System.out.println("evaluated... taken: " + (System.currentTimeMillis() - timeInit));
			double significance = c.getSignificance(); 
			//val = c.getSignificance() * c.evaluate(sessions).getValue();
			val = significance * eval;
//			System.out.println("Eval: " + eval + " : significance " + significance + " : factor " + val);
		}
		System.out.println("Constraints evaluated. Fitness: " + (1/val) + "  taken: " + (System.currentTimeMillis() - timeInit) +" ms");
		return 1 / val;
	}
	
/*	if((session[i].getIsTheorical() == true) && (!(session[i].getRoom().getType().equals( "Y") )))
		return ConstraintValue.ONE;
		 return ConstraintValue.ZERO;*/
	
}
