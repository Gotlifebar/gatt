package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.dialogs.DetailConstraintDialog;

public class CloseDetailConstraintDialogAction extends AbstractAction {
	
	private DetailConstraintDialog dialog;
	
	public CloseDetailConstraintDialogAction(DetailConstraintDialog dialog){
		this.dialog = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
		dialog.dispose();
	}

}
