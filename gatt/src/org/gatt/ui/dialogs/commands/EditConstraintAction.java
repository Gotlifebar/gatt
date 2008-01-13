package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.io.XMLConstraintWriter;
import org.gatt.ui.dialogs.ManageConstraintsDialog;
import org.gatt.ui.wizards.ConstraintWizard;
import org.gatt.ui.wizards.helper.ConstraintWizardInfoWrapper;

public class EditConstraintAction extends AbstractAction {
	
	private ManageConstraintsDialog dialog;
	
	private final int ID_COLUMN = 0;
	
	public EditConstraintAction(ManageConstraintsDialog dialog){
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
			
			XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
			DefaultTableModel model = (DefaultTableModel)dialog.getTabConstraints().getModel();
			String id = (String)model.getValueAt(row, ID_COLUMN);
			
			ConstraintWizard wizard = new ConstraintWizard(writer.readConstraintWrapper(id));
			
			if(wizard.isCanceled())
				return;
			
			ConstraintInfo constraint = wizard.getConstraintProducer().getProducedConstraint();
			ConstraintWizardInfoWrapper cWrapper = wizard.getConstraintWrapper();
			cWrapper.setConstraintInfo(constraint);
			
			dialog.updateConstraint(cWrapper);
			
		}

	}

}
