package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateConditionalComparisonPanel;

public class CompTypeAttributeFormerSelectedAction extends AbstractAction {

private CreateConditionalComparisonPanel panel;
	
	public CompTypeAttributeFormerSelectedAction(CreateConditionalComparisonPanel panel){
		this.panel = panel;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight().setEnabled(true);
		panel.getTfConstantValue().setEnabled(false);

	}

}
