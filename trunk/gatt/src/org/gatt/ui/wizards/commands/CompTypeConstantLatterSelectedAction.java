package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateIfThenComparisonPanel;

public class CompTypeConstantLatterSelectedAction extends AbstractAction {

	private CreateIfThenComparisonPanel panel;
	
	public CompTypeConstantLatterSelectedAction(CreateIfThenComparisonPanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight1().setEnabled(false);
		panel.getTfConstantValue1().setEnabled(true);

	}

}
