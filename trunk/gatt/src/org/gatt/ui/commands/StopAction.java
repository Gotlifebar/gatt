package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

public class StopAction extends AbstractAction {

	private GattFrame frame;
	
	public StopAction(GattFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Stop Action");
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		opFacade.stopOptimization();
		frame.hideProgressPanel();
		frame.enableOptimizationCommands();
		frame.disablePauseCommands();
		frame.disableStopCommands();
		frame.enableOptions();
	}

}
