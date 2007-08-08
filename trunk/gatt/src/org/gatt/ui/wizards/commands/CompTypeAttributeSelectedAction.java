package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import org.gatt.ui.wizards.CreateComparationPanel;

public class CompTypeAttributeSelectedAction extends AbstractAction {
	
	private JComponent parent;
	
	public CompTypeAttributeSelectedAction(JComponent parent){
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent e) {
		CreateComparationPanel panel = (CreateComparationPanel)parent;
		panel.getTreeRight().setEnabled(true);
		panel.getTextFreeDomainValue().setEnabled(false);
	}

}
