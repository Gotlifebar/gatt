package org.gatt.optimization.ttga;

import java.util.Vector;

import org.gatt.constraint.Constraint;
import org.gatt.constraint.ConstraintFacade;
import org.gatt.domain.Hour;
import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.optimization.util.NumericTransformationFunction;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class TTFitnessFunction extends FitnessFunction {
	
	private NumericTransformationFunction ntf;
	
	public TTFitnessFunction(int countRooms, int countHours){
		super();
		ntf = new NumericTransformationFunction(countRooms, countHours);
		
	}
	
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
		//make the transformation.... using the Facade to retrieve by id's ..
		//and the NumericTransformationFunction to get the index information.
		DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
		Gene[] gene = individual.getGenes();
		Session[] sessions = new Session[gene.length];		
		for(int i = 0; i < gene.length; i++){
			if( (Integer)gene[i].getAllele() <= 0 )//It's a blank space, there's not need to process it
				continue;
				
			sessions[i] = new Session();
			//the GET's of the DomainObjectFactoryFacade can be improved by
			//using a memory cache, that can be in the DAO's or in the Facade itself.
			sessions[i].setHour(doff.getHour(ntf.getHourIdFrom(i)));
			sessions[i].setRoom(doff.getRoom(ntf.getRoomIdFrom(i)));
			sessions[i].setGroup(doff.getGroup((Integer)gene[i].getAllele()));			
		}
		return sessions;
	}
	
}
