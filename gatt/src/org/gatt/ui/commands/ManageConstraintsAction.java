package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.ManageConstraintsDialog;

/**
 * @author Chucho
 * Manage constraints command
 */
public class ManageConstraintsAction extends AbstractAction {

	/**
	 * Frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public ManageConstraintsAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Manage Constraints Action");
		ManageConstraintsDialog dialog = new ManageConstraintsDialog(frame);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

}
