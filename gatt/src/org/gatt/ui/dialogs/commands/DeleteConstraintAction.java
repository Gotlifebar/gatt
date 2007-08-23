package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.gatt.constraint.io.XMLConstraintWriter;
import org.gatt.ui.dialogs.ManageConstraintsDialog;

public class DeleteConstraintAction extends AbstractAction {

	private ManageConstraintsDialog dialog;
	
	public DeleteConstraintAction(ManageConstraintsDialog dialog) {
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		int row = dialog.getTabConstraints().getSelectedRow();
		
		if(row == -1){
			JOptionPane.showMessageDialog(dialog, 
										"Debe seleccionar un restricción primero",
										"Administrar restricciones",
										JOptionPane.WARNING_MESSAGE);
			return;
		}else{
			int response = JOptionPane.showConfirmDialog(dialog, 
					"Está seguro de eliminar la restricción seleccionada?",
					"Administrar de restricciones",
					JOptionPane.YES_NO_CANCEL_OPTION); 
			
			switch(response){
			
				case JOptionPane.YES_OPTION:
					DefaultTableModel model = (DefaultTableModel)dialog.getTabConstraints().getModel();
					String id = (String)model.getValueAt(row, 0);
					
					XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
					writer.deleteConstraint(id);
					
					model.removeRow(row);
					break;
			}
			
		}
		
	}

}
