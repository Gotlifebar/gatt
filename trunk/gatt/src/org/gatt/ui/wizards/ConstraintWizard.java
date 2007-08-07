package org.gatt.ui.wizards;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import org.freixas.jwizard.JWizardDialog;

public class ConstraintWizard extends JWizardDialog {
	
	public ConstraintWizard(){
		
		setModal(true);
		
		this.setTitle("Asistente para la definición de restricciones");
		
		this.setPreferredSize(new Dimension(620, 455));
		
		// Create each step
		addWizardPanel(new AddConstraintPanel());
	    addWizardPanel(new CreateComparationPanel());
	    addWizardPanel(new CreateHandSidePanel());
	    addWizardPanel(new ConstraintPreviewPanel());
		
		this.disableCancelAtEnd();
		
		pack();
		setVisible(true);
	}
	
	protected void cancel(){
		int response =
			JOptionPane.showConfirmDialog(
			    this,
			    "Cancelar Asistente?",
			    "Cancelar Asistente ",
			    JOptionPane.OK_CANCEL_OPTION);

		if (response == JOptionPane.OK_OPTION) {
		 	super.cancel();
		}
	}
	
	public static void	main(
	    String[] args)
	{
	    new ConstraintWizard();
	    System.exit(0);
	}
}
