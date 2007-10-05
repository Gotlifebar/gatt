package org.gatt.ui.wizards;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import org.freixas.jwizard.JWizardDialog;
import org.gatt.constraint.ConstraintInfo;
import org.gatt.ui.wizards.helper.ConstraintWizardInfoWrapper;
import org.gatt.ui.wizards.helper.ConstraintWizardProducer;

public class ConstraintWizard extends JWizardDialog {
	
	public enum ComplementType {AND, OR}
	public enum ConstraintType {CONDITIONAL, SIMPLE}
	
	
	
	private int currentRound;
	private int lastRound;
	private ComplementType complementType;
	private ConstraintType constraintType;
	
	private ConstraintWizardProducer constraintProducer;
	
	public static final int DEFINING_OPERATION = 0,
							COMPLEMENTING_OPERATION = 1;
	
	private static final int ADD_CONSTRAINT_PANEL = 0,
							 CREATE_SIMPLE_COMPARISON_PANEL = 1,
							 CREATE_CONDITIONAL_COMPARISON_PANEL = 2,
							 CONSTRAINT_PREVIEW_PANEL = 3;
	
	private int currentOperation;
	
	
	private boolean canceled = false;
	private ConstraintWizardInfoWrapper constraintWrapper;
	private boolean editing;
	
	public ConstraintWizard(){
		
		setModal(true);
		currentOperation = DEFINING_OPERATION;
		currentRound = 1;
		lastRound = 0;
		complementType = null;
		constraintProducer = new ConstraintWizardProducer();
		constraintWrapper = new ConstraintWizardInfoWrapper();
		editing = false;
		
		this.setTitle("Asistente para la definición de restricciones");
		
		this.setPreferredSize(new Dimension(620, 455));
		
		// Create each step
		addWizardPanel(new AddConstraintPanel());
	    addWizardPanel(new CreateSimpleComparisonPanel());
	    addWizardPanel(new CreateConditionalComparisonPanel());
	    addWizardPanel(new ConstraintPreviewPanel());
		
		this.disableCancelAtEnd();
				
		pack();
		setVisible(true);
	}
	
	public ConstraintWizard(ConstraintWizardInfoWrapper constraintWrapper){

		setModal(true);
		currentOperation = DEFINING_OPERATION;
		currentRound = 1;
		lastRound = 0;
		complementType = null;
		constraintProducer = new ConstraintWizardProducer();
		this.constraintWrapper = constraintWrapper; 
		editing = true;
		constraintProducer.setConstraintId(constraintWrapper.getConstraintInfo().getId());
		
		this.setTitle("Asistente para la definición de restricciones");
		
		this.setPreferredSize(new Dimension(620, 455));
		
		// Create each step
		addWizardPanel(new AddConstraintPanel());
	    addWizardPanel(new CreateSimpleComparisonPanel());
	    addWizardPanel(new CreateConditionalComparisonPanel());
	    addWizardPanel(new ConstraintPreviewPanel());
		
		this.disableCancelAtEnd();
				
		pack();
		setVisible(true);
	}
	
	public boolean isEditing(){
		return editing;
	}
	
	public ConstraintWizardInfoWrapper getConstraintWrapper(){
		return constraintWrapper;
	}
	
	public boolean isCanceled(){
		return canceled;
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

	public ComplementType getComplementType() {
		return complementType;
	}

	public void setComplementType(ComplementType comparisonType) {
		this.complementType = comparisonType;
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
			canceled = true;
		 	super.cancel();
		}
	}

	public int getCurrentOperation() {
		return currentOperation;
	}
	
	public void setCurrentOperation(int currentOperation) {
		this.currentOperation = currentOperation;
	}
	
	public ConstraintInfo getProducedConstraint(){
		return constraintProducer.getProducedConstraint();
	}
	
	public int getNextPanel(){
		int next = -1;		
		switch(this.getCurrentStep()){
			case ADD_CONSTRAINT_PANEL:
				currentOperation = DEFINING_OPERATION;
				constraintProducer.setCurrentExpression(ConstraintWizardProducer.CONDITION_EXPRESSION);
				if(constraintType == ConstraintType.SIMPLE)
					next = CREATE_SIMPLE_COMPARISON_PANEL;
				else
					next = CREATE_CONDITIONAL_COMPARISON_PANEL;
				break;
			case CREATE_SIMPLE_COMPARISON_PANEL:
				//currentOperation = DEFINING_OPERATION;
				next = CONSTRAINT_PREVIEW_PANEL;
				break;
			case CREATE_CONDITIONAL_COMPARISON_PANEL:
				next = CONSTRAINT_PREVIEW_PANEL;
				break;
			/*case CREATE_HAND_SIDE_PANEL:
				if( currentOperation == COMPLEMENTING_OPERATION ){
					currentOperation = DEFINING_OPERATION;
					next = CREATE_COMPARISON_PANEL;
					break;
				}
				if( constraintProducer.getConstraintType() == (ConstraintType.SIMPLE.ordinal() + 1)){
					System.out.println(constraintProducer.getConstraintCode());
					next = CONSTRAINT_PREVIEW_PANEL;
					XMLConstraintWriter writer = XMLConstraintWriter.getInstance();						
					writer.write(constraintProducer.getProducedConstraint());
					break;
				}
				switch(constraintProducer.getCurrentExpression()){
					case ConstraintWizardProducer.CONDITION_EXPRESSION:
						constraintProducer.setCurrentExpression(ConstraintWizardProducer.CONSEQUENCE_EXPRESSION);
						currentOperation = DEFINING_OPERATION;
						next = CREATE_COMPARISON_PANEL;
						break;
					case ConstraintWizardProducer.CONSEQUENCE_EXPRESSION:
						next = CONSTRAINT_PREVIEW_PANEL;
						System.out.println(constraintProducer.getConstraintCode());
						XMLConstraintWriter writer = XMLConstraintWriter.getInstance();						
						writer.write(constraintProducer.getProducedConstraint());
						break;
				}
				break;*/
			case CONSTRAINT_PREVIEW_PANEL:
				//TODO: o_O !?
				//System.out.println(constraintProducer.getConstraintCode());
				break;	
		}
		return next;
	}
	
	/*private void saveConstraint(){
		XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
		writer.write(constraintProducer.getProducedConstraint());
	}*/
	
}
