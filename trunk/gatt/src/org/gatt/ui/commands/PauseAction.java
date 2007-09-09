package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

public class PauseAction extends AbstractAction {
	
	private GattFrame frame;
	
	public PauseAction(GattFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Pause Action");
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		opFacade.pauseOptimization();
		frame.enableOptimizationCommands();
		frame.disablePauseCommands();
	}

}
