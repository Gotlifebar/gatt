package org.gatt.optimization.ttga;

import java.util.Vector;

import org.gatt.domain.Hour;
import org.gatt.domain.Room;
import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.optimization.util.NumericTransformationFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class SolutionManager {
	private Session[] solutionCache;
	private NumericTransformationFunction ntf;
	
	public SolutionManager(NumericTransformationFunction ntf){
		solutionCache = null;
		this.ntf = ntf;
	}
	public Vector<Session> filterSolutionByRoom(IChromosome solution, Room r){
		if( solutionCache == null )
			solutionCache = transform(solution);
		Vector<Session> filtered = new Vector<Session>();
		for(Session session: solutionCache)
			if( (session != null) && (session.getRoom().getId() == r.getId()) )
				filtered.add(session);
		
		return filtered;
	}
	public Vector<Session> filterSolutionByDay(IChromosome solution, int day){
		if(solutionCache == null)
			solutionCache = transform(solution);
		Vector<Session> filtered = new Vector<Session>();
		for(Session session: solutionCache)
			if( (session != null) && (session.getHour().getDay() == day) )
				filtered.add(session);
		
		return filtered;
	}
	public Session[] transform(IChromosome individual){		
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
