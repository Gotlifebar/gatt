package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateConditionalComparisonPanel;

public class CompTypeConstantLatterSelectedAction extends AbstractAction {

	private CreateConditionalComparisonPanel panel;
	
	public CompTypeConstantLatterSelectedAction(CreateConditionalComparisonPanel panel){
		this.panel = panel;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight1().setEnabled(false);
		panel.getTfConstantValue1().setEnabled(true);

	}

}
