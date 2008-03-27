package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.dialogs.DetailConstraintDialog;

/**
 * @author Chucho
 * Close command in the Detail constraint dialog
 */
public class CloseDetailConstraintDialogAction extends AbstractAction {
	
	/**
	 * the dialog
	 */
	private DetailConstraintDialog dialog;
	
	/**
	 * constructor
	 * @param dialog
	 */
	public CloseDetailConstraintDialogAction(DetailConstraintDialog dialog){
		this.dialog = dialog;
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
		dialog.dispose();
	}

}
