package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import org.gatt.ui.wizards.CreateComparationPanel;

public class CompTypeConstantSelectedAction extends AbstractAction {
	
	private JComponent parent;
	
	public CompTypeConstantSelectedAction(JComponent parent){
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent e) {
		CreateComparationPanel panel = (CreateComparationPanel)parent;
		panel.getTreeRight().setEnabled(false);
		panel.getTextFreeDomainValue().setEnabled(true);
		
	}

}
