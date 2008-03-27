package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

/**
 * @author Chucho
 * Optimize command
 */
public class OptimizeAction extends AbstractAction {
	
	/**
	 * frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public OptimizeAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Optimize Action");
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		frame.showProgressPanel();
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.PAUSED){
			frame.restartProgressBar();
			opFacade.resumeOptimization();
		}else{
			frame.increaseProgressPanel(0);
			opFacade.optimize();
		}
		frame.disableOptimizationCommands();
		frame.enablePauseCommands();
		frame.enableStopCommands();
		frame.disableOptions();
	}

}
