package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.gatt.ui.wizards.CreateConditionalComparisonPanel;

public class DeleteComparisonLatterAction extends AbstractAction {

	
	private CreateConditionalComparisonPanel panel;
	
	public DeleteComparisonLatterAction(CreateConditionalComparisonPanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		int[] indexes = panel.getListComparisons1().getSelectedIndices();
		
		if(indexes.length == 0){
			JOptionPane.showMessageDialog(panel.getWizardParent(),
					"Debe seleccionar uno ó más elementos de la lista",
					"Administración de restricciones",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		for (int i = 0; i < indexes.length; i++) {
			DefaultListModel listModel = (DefaultListModel)panel.getListComparisons1().getModel();
			listModel.remove(indexes[i]);
		}
		
		if(panel.getListComparisons1().getModel().getSize() == 0)
			panel.deshabilitarAndOr1();
	}

}
