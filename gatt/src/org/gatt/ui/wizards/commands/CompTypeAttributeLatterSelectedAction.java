package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateIfThenComparisonPanel;

public class CompTypeAttributeLatterSelectedAction extends AbstractAction {

private CreateIfThenComparisonPanel panel;
	
	public CompTypeAttributeLatterSelectedAction(CreateIfThenComparisonPanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight1().setEnabled(true);
		panel.getTfConstantValue1().setEnabled(false);

	}

}
