package org.gatt.optimization.util;

import java.io.Serializable;
import java.util.Vector;

import org.gatt.domain.Room;
import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 * @author Chucho
 * Solution manager
 */
public class SolutionManager implements Serializable {
	
	/**
	 * cache of sessions 
	 */
	private Session[] solutionCache;
	/**
	 * NumericTransformationFunction object
	 */
	private NumericTransformationFunction ntf;
	
	/**
	 * days letters
	 */
	private static final String[] dayToString = {"L", "M", "W", "J", "V", "S"};
	
	/**
	 * constructor
	 * @param ntf
	 */
	public SolutionManager(NumericTransformationFunction ntf){
		solutionCache = null;
		this.ntf = ntf;
	}
	/**
	 * Filter a solution by room and returns a vector of sessions
	 * @param solution
	 * @param r
	 */
	public Vector<Session> filterSolutionByRoom(IChromosome solution, Room r){
		if( solutionCache == null )
			solutionCache = transform(solution);
		Vector<Session> filtered = new Vector<Session>();
		for(Session session: solutionCache)
			if( (session != null) && (session.getRoom().getId() == r.getId()) )
				filtered.add(session);
		
		return filtered;
	}
	/**
	 * @param solution
	 * @param day
	 * Filter a solution by day and returns a vector of sessions
	 */
	public Vector<Session> filterSolutionByDay(IChromosome solution, int day){
		if(solutionCache == null)
			solutionCache = transform(solution);
		Vector<Session> filtered = new Vector<Session>();
		
		for(Session session: solutionCache){
			/*if( session == null ){
				System.out.println("La sesión está nula");
				System.exit(1);
			}
			if( session.getHour() == null ){
				System.out.println("La Hora está nula");
				System.exit(1);
			}*/
			
			if( (session != null) && session.getHour().getRepresentation().contains(dayToString[day]) )
				filtered.add(session);
		}
		return filtered;
	}
	/**
	 * @param individual
	 * make the transformation.... using the Facade to retrieve by id's ..
	 * and the NumericTransformationFunction to get the index information.
	 */
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
			Session inarr = sessions[i];
			Session inmem = doff.getSession((Integer)gene[i].getAllele());
			sessions[i].setGroup(doff.getGroup((Integer)gene[i].getAllele()));//doff.getSession((Integer)gene[i].getAllele()).getGroup());
			//sessions[i].setGroup(doff.getSession((Integer)gene[i].getAllele()).getGroup());
		}
		return sessions;
	}
}
