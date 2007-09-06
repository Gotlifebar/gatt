package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateConditionalComparisonPanel;

public class CompTypeConstantFormerSelectedAction extends AbstractAction {

	private CreateConditionalComparisonPanel panel;
	
	public CompTypeConstantFormerSelectedAction(CreateConditionalComparisonPanel panel){
		this.panel = panel;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight().setEnabled(false);
		panel.getTfConstantValue().setEnabled(true);

	}

}
