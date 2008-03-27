package org.gatt.optimization.ttga;

import java.util.Vector;

import org.gatt.ui.GattFrame;
import org.jgap.Genotype;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;

/**
 * @author Chucho
 * A listener of the timetabling evolution process events
 */
public class TimeTablingEvolutionListener implements GeneticEventListener {
	
	/**
	 * Evolution thread
	 */
	private Thread evolutionThread;	
	
	/**
	 * flag that indicates if the thread is suspended 
	 */
	private boolean suspendedFlag = false;
	
	/**
	 * last time 
	 */
	private long lastTime;
	
	/**
	 * vector of fitness values
	 */
	private Vector<Double> fitnessValues;
	/**
	 * vector of times
	 */
	private Vector<Long> times;
	/**
	 * vector of durations
	 */
	private Vector<Float> durations;
	
	/**
	 * constructor
	 * @param evolutionThread
	 */
	public TimeTablingEvolutionListener(Thread evolutionThread){
		this.evolutionThread = evolutionThread;
		fitnessValues = new Vector<Double>();
		lastTime = System.currentTimeMillis();
		times = new Vector<Long>();
		durations = new Vector<Float>();
	}
	
	
	/* (non-Javadoc)
	 * @see org.jgap.event.GeneticEventListener#geneticEventFired(org.jgap.event.GeneticEvent)
	 */
	public void geneticEventFired(GeneticEvent firedEvent) {
		long taken = System.currentTimeMillis();
		times.add(taken);
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.PAUSED){
			evolutionThread.suspend();
			suspendedFlag = true;
		}
		
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.RUNNING){
			if(suspendedFlag){
				suspendedFlag = false;
			}else{
				Genotype genotype = (Genotype)firedEvent.getSource();
				
				fitnessValues.add(genotype.getFittestChromosome().getFitnessValue());
				durations.add(((float)(taken - lastTime)) / 1000);
				System.out.println("Time Taken: " + ((float)(taken - lastTime)) / 1000  + " seconds");
				
				GattFrame frame = GattFrame.getInstance();
				frame.increaseProgressPanel(genotype.getConfiguration().getGenerationNr());
			}
		}
		lastTime = System.currentTimeMillis();
	}


	/**
	 * return a vector of durations
	 */
	public Vector<Float> getDurations() {
		return durations;
	}


	/**
	 * return a vector of fitness values
	 */
	public Vector<Double> getFitnessValues() {
		return fitnessValues;
	}


	/**
	 * return a vector of times
	 */
	public Vector<Long> getTimes() {
		return times;
	}
	

}
