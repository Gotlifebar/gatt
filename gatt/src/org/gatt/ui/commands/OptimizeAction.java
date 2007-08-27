package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

public class OptimizeAction extends AbstractAction {
	
	private GattFrame frame;
	
	public OptimizeAction(GattFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Optimize Action");
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		
		if(opFacade.getOptimizationState() == OptimizationFacade.OptimizationState.PAUSED){
			opFacade.resumeOptimization();
		}else{
			opFacade.optimize();
		}
		frame.disableOptimizationCommands();
		frame.enablePauseCommands();
		frame.enableStopCommands();
	}

}
