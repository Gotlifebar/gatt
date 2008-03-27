package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Chucho
 * Occupation time command
 */
public class OccupationTimeAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Occupation Time Action");

	}

}
