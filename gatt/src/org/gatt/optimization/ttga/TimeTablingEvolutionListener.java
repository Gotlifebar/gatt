package org.gatt.optimization.ttga;

import java.util.Vector;

import org.gatt.ui.GattFrame;
import org.jgap.Genotype;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;

public class TimeTablingEvolutionListener implements GeneticEventListener {
	
	private Thread evolutionThread;	
	private boolean suspendedFlag = false;
	private long lastTime;
	
	private Vector<Double> fitnessValues;
	private Vector<Long> times;
	private Vector<Float> durations;
	
	public TimeTablingEvolutionListener(Thread evolutionThread){
		this.evolutionThread = evolutionThread;
		fitnessValues = new Vector<Double>();
		lastTime = System.currentTimeMillis();
		times = new Vector<Long>();
		durations = new Vector<Float>();
	}
	
	
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


	public Vector<Float> getDurations() {
		return durations;
	}


	public Vector<Double> getFitnessValues() {
		return fitnessValues;
	}


	public Vector<Long> getTimes() {
		return times;
	}
	

}
