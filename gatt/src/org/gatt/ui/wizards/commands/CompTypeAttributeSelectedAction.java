package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import org.gatt.ui.wizards.CreateComparisonPanel;

public class CompTypeAttributeSelectedAction extends AbstractAction {
	
	private CreateComparisonPanel panel;
	
	public CompTypeAttributeSelectedAction(CreateComparisonPanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight().setEnabled(true);
		panel.getTextFreeDomainValue().setEnabled(false);
	}

}
