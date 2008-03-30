package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.gatt.optimization.io.SolutionIO;
import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;
import org.jgap.IChromosome;

/**
 * @author Chucho
 * Optimize previous action
 */
public class OptimizePreviousAction extends AbstractAction {
	
	/**
	 * frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public OptimizePreviousAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		SolutionIO s = new SolutionIO();
		
		IChromosome previousSolution;
		
		try{
			previousSolution = s.loadSolution();
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, 
					"Ocurrió un error intentando cargar la solución.",
					"Optimizar desde solución anterior", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		frame.showProgressPanel();
		frame.restartProgressBar();
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.PAUSED){
			int answer = JOptionPane.showConfirmDialog(frame, 
					"Actualmente hay un proceso de optimizacion en ejecucion.\n" +
					"Desea cancelarlo para iniciar uno nuevo a partir de una solucion anterior?",
					"Optimizat desde solucion anterior",JOptionPane.YES_NO_OPTION);
			
			if(answer == JOptionPane.YES_OPTION){
				frame.increaseProgressPanel(0);
				opFacade.optimizeFromPreviousSolution(previousSolution);
			}else{
				return;
			}
			
		}
		
		frame.increaseProgressPanel(0);
		opFacade.optimizeFromPreviousSolution(previousSolution);
		
		frame.disableOptimizationCommands();
		frame.enablePauseCommands();
		frame.enableStopCommands();
		frame.disableOptions();

	}

}
