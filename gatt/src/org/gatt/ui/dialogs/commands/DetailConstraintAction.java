package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.gatt.constraint.io.XMLConstraintRepository;
import org.gatt.ui.dialogs.DetailConstraintDialog;
import org.gatt.ui.dialogs.ManageConstraintsDialog;
import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;

/**
 * @author Chucho
 * Detail command in the constraint dialog
 */
public class DetailConstraintAction extends AbstractAction {

	/**
	 * the dialog
	 */
	private ManageConstraintsDialog dialog;
	
	private final int ID_COLUMN = 0;
	
	/**
	 * constructor
	 * @param dialog
	 */
	public DetailConstraintAction(ManageConstraintsDialog dialog){
		this.dialog = dialog;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		int row = dialog.getTabConstraints().getSelectedRow();
		
		if(row == -1){
			JOptionPane.showMessageDialog(dialog, 
										"Debe seleccionar un restricci�n primero",
										"Administrar restricciones",
										JOptionPane.WARNING_MESSAGE);
			return;
		}else{
			JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
			JFigIF config = JFig.getInstance(locator);
			
			String sFile = null;
			try{
				sFile = config.getValue("XMLWriterInfo", "FilePath");
			}catch(JFigException jFigEx){
				jFigEx.printStackTrace();
			}
			
			XMLConstraintRepository repository = new XMLConstraintRepository(new File(sFile));
			repository.load();
			
			DefaultTableModel model = (DefaultTableModel)dialog.getTabConstraints().getModel();
			String id = (String)model.getValueAt(row, ID_COLUMN);
						
			DetailConstraintDialog detailDialog = new DetailConstraintDialog(dialog,repository.getConstraintById(id));
			detailDialog.setModal(true);
			detailDialog.setVisible(true);
		}

	}

}
