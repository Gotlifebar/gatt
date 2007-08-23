package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.ManageConstraintsDialog;

public class ManageConstraintsAction extends AbstractAction {

	private GattFrame frame;
	
	public ManageConstraintsAction(GattFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Manage Constraints Action");
		ManageConstraintsDialog dialog = new ManageConstraintsDialog(frame);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

}
