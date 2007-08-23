package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.ui.dialogs.ManageConstraintsDialog;
import org.gatt.ui.wizards.ConstraintWizard;

public class NewConstraintAction extends AbstractAction {
	
	private ManageConstraintsDialog dialog;
	
	public NewConstraintAction(ManageConstraintsDialog dialog) {
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		ConstraintWizard wizard = new ConstraintWizard();
		ConstraintInfo constraint = wizard.getConstraintProducer().getProducedConstraint();
		
		dialog.addConstraint(constraint);
	}

}
