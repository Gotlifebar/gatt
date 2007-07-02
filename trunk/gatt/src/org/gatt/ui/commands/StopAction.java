package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class StopAction extends AbstractAction {

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Stop Action");
	}

}
