package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.gatt.ui.wizards.CreateSimpleComparisonPanel;

/**
 * @author Chucho
 *
 */
public class DeleteComparisonFormerSimpleComparisonAction extends AbstractAction {

	
	/**
	 * the panel in which this command is used
	 */
	private CreateSimpleComparisonPanel panel;
	
	/**
	 * constructor
	 * @param panel
	 */
	public DeleteComparisonFormerSimpleComparisonAction(CreateSimpleComparisonPanel panel){
		this.panel = panel;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		int[] indexes = panel.getListComparisons().getSelectedIndices();
		
		if(indexes.length == 0){
			JOptionPane.showMessageDialog(panel.getWizardParent(),
					"Debe seleccionar uno ó más elementos de la lista",
					"Administración de restricciones",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		System.out.println("Numero indices: " + indexes.length);
		
		for (int i = 0; i < indexes.length; i++) {
			System.out.println("Indice especifico: " + indexes[i]);
			DefaultListModel listModel = (DefaultListModel)panel.getListComparisons().getModel();
			listModel.remove(indexes[i]);
		}
		
		if(panel.getListComparisons().getModel().getSize() == 0)
			panel.deshabilitarAndOr();
	}

}
