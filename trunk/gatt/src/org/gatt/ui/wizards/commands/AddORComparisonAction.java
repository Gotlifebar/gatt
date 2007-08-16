package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.ConstraintWizard;
import org.gatt.ui.wizards.CreateHandSidePanel;

public class AddORComparisonAction extends AbstractAction {
	
	private CreateHandSidePanel panel;
	
	public AddORComparisonAction(CreateHandSidePanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		ConstraintWizard wizard = (ConstraintWizard)panel.getWizardParent();
		wizard.setCurrentRound(wizard.getCurrentRound() + 1);
		wizard.setComplementType(ConstraintWizard.ComplementType.OR);
		System.out.println("voy a complementar con or");
		wizard.setCurrentOperation(ConstraintWizard.COMPLEMENTING_OPERATION);
		panel.doNext();
	}

}
