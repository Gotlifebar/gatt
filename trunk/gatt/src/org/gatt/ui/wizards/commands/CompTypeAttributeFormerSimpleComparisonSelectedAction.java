package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateSimpleComparisonPanel;

public class CompTypeAttributeFormerSimpleComparisonSelectedAction extends AbstractAction {

private CreateSimpleComparisonPanel panel;
	
	public CompTypeAttributeFormerSimpleComparisonSelectedAction(CreateSimpleComparisonPanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight().setEnabled(true);
		panel.getTfConstantValue().setEnabled(false);

	}

}
