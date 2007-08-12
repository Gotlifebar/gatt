package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import org.gatt.ui.wizards.CreateComparisonPanel;

public class CompTypeConstantSelectedAction extends AbstractAction {
	
	private CreateComparisonPanel panel;
	
	public CompTypeConstantSelectedAction(CreateComparisonPanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight().setEnabled(false);
		panel.getTextFreeDomainValue().setEnabled(true);
		
	}

}
