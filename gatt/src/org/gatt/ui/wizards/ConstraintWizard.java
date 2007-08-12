package org.gatt.ui.wizards;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import org.freixas.jwizard.JWizardDialog;
import org.gatt.constraint.ConstraintInfo;
import org.gatt.ui.wizards.helper.ConstraintWizardProducer;

public class ConstraintWizard extends JWizardDialog {
	
	public enum ComparisonType {AND, OR}
	public enum ConstraintType {SIMPLE, CONDITIONAL}
	
	private int currentRound;
	private int lastRound;
	private ComparisonType comparisonType;
	private ConstraintType constraintType;
	
	private ConstraintWizardProducer constraintProducer;
	
	public ConstraintWizard(){
		
		setModal(true);
		
		currentRound = 1;
		lastRound = 0;
		
		constraintProducer = new ConstraintWizardProducer();
		
		this.setTitle("Asistente para la definición de restricciones");
		
		this.setPreferredSize(new Dimension(620, 455));
		
		// Create each step
		addWizardPanel(new AddConstraintPanel());
	    addWizardPanel(new CreateComparisonPanel());
	    addWizardPanel(new CreateHandSidePanel());
	    addWizardPanel(new ConstraintPreviewPanel());
		
		this.disableCancelAtEnd();
		
		pack();
		setVisible(true);
	}
	
	
		
	public ConstraintType getConstraintType() {
		return constraintType;
	}

	public void setCType(ConstraintType type) {
		constraintType = type;
	}

	public ConstraintWizardProducer getConstraintProducer() {
		return constraintProducer;
	}

	public ComparisonType getComparisonType() {
		return comparisonType;
	}

	public void setComparisonType(ComparisonType comparisonType) {
		this.comparisonType = comparisonType;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public int getLastRound() {
		return lastRound;
	}

	public void setLastRound(int lastRound) {
		this.lastRound = lastRound;
	}
	
	public boolean isSameRound(){
		return lastRound == currentRound;
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
