package org.gatt.optimization.ttga;

import org.gatt.ui.GattFrame;
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
			evolutionThread.suspend();
			suspendedFlag = true;
		}
		
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.RUNNING){
			if(suspendedFlag){
				suspendedFlag = false;
			}else{
				Genotype genotype = (Genotype)firedEvent.getSource();
				GattFrame frame = GattFrame.getInstance();
				frame.increaseProgressPanel(genotype.getConfiguration().getGenerationNr());
			}
		}
		
	}

}
