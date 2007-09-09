package org.gatt.optimization.ttga;

import org.jgap.Genotype;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;

public class TimeTablingEvolutionListener implements GeneticEventListener {
	
	private Thread evolutionThread;
	
	private boolean suspendedFlag = false;
	
	public TimeTablingEvolutionListener(Thread evolutionThread){
		this.evolutionThread = evolutionThread;
	}
	
	
	public void geneticEventFired(GeneticEvent firedEvent) {
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.PAUSED){
			System.out.println("Thread suspended");
			evolutionThread.suspend();
			suspendedFlag = true;
		}
		
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.RUNNING){
			if(suspendedFlag){
				System.out.println("Restarting thread");
				suspendedFlag = false;
			}else{
				Genotype genotype = (Genotype)firedEvent.getSource();
				System.out.println("Generation number: " + genotype.getConfiguration().getGenerationNr());
			}
		}
		
	}

}
