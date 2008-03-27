package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

/**
 * @author Chucho
 * pause command
 */
public class PauseAction extends AbstractAction {
	
	/**
	 * frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public PauseAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Pause Action");
		OptimizationFacade opFacade = OptimizationFacade.getInstance();
		opFacade.pauseOptimization();
		frame.pauseProgressBar();
		frame.enableOptimizationCommands();
		frame.disablePauseCommands();
	}

}
