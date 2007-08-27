package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateIfThenComparisonPanel;

public class CompTypeConstantFormerSelectedAction extends AbstractAction {

	private CreateIfThenComparisonPanel panel;
	
	public CompTypeConstantFormerSelectedAction(CreateIfThenComparisonPanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight().setEnabled(false);
		panel.getTfConstantValue().setEnabled(true);

	}

}
