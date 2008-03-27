package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

/**
 * @author Chucho
 * stop command
 */
public class StopAction extends AbstractAction {

	/**
	 * frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public StopAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
