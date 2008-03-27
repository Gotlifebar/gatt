package org.gatt.ui.wizards;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import org.freixas.jwizard.JWizardDialog;
import org.gatt.constraint.ConstraintInfo;
import org.gatt.ui.wizards.helper.ConstraintWizardInfoWrapper;

import org.gatt.ui.wizards.helper.ConstraintWizardProducer;

/**
 * @author Chucho
 * constriant wizard
 */
public class ConstraintWizard extends JWizardDialog {
	
	/**
	 * @author Chucho
	 * Enumeration of complement types
	 */
	public enum ComplementType {AND, OR}
	/**
	 * @author Chucho
	 * Enumaration of constraint types
	 */
	public enum ConstraintType {CONDITIONAL, SIMPLE}
	
	/**
	 * current round 
	 */
	private int currentRound;
	/**
	 * last round
	 */
	private int lastRound;
	/**
	 * complement type
	 */
	private ComplementType complementType;
	/**
	 * constraint type
	 */
	private ConstraintType constraintType;
	
	/**
	 * constraint producer
	 */
	private ConstraintWizardProducer constraintProducer;
	
	public static final int DEFINING_OPERATION = 0,
							COMPLEMENTING_OPERATION = 1;
	
	private static final int ADD_CONSTRAINT_PANEL = 0,
							 CREATE_SIMPLE_COMPARISON_PANEL = 1,
							 CREATE_CONDITIONAL_COMPARISON_PANEL = 2,
							 CONSTRAINT_PREVIEW_PANEL = 3;
	
	/**
	 * current operation
	 */
	private int currentOperation;
	
	
	private boolean canceled = false;
	/**
	 * constraint info wrapper
	 */
	private ConstraintWizardInfoWrapper constraintWrapper;
	
	/**
	 * says if a constriant is being edited 
	 */
	private boolean editing;
	
	/**
	 * cosntructor 
	 */
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
	
	/**
	 * constructor
	 * @param constraintWrapper
	 */
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
	
	/**
	 * return whether the wizard is being used to edit a constriant or to create a new one
	 */
	public boolean isEditing(){
		return editing;
	}
	
	/**
	 * return the constraint info wrapper
	 */
	public ConstraintWizardInfoWrapper getConstraintWrapper(){
		return constraintWrapper;
	}
	
	/**
	 * return whether the wizard has been canceled or not
	 */
	public boolean isCanceled(){
		return canceled;
	}
		
	/**
	 * return the constraint type
	 */
	public ConstraintType getConstraintType() {
		return constraintType;
	}

	/**
	 * sets the constriant type
	 * @param type
	 */
	public void setCType(ConstraintType type) {
		constraintType = type;
	}

	/**
	 * return the constraint producer
	 */
	public ConstraintWizardProducer getConstraintProducer() {
		return constraintProducer;
	}

	/**
	 * return the complement type
	 */
	public ComplementType getComplementType() {
		return complementType;
	}

	/**
	 * sets the complement type
	 * @param comparisonType
	 */
	public void setComplementType(ComplementType comparisonType) {
		this.complementType = comparisonType;
	}

	/**
	 * return current round
	 */
	public int getCurrentRound() {
		return currentRound;
	}

	/**
	 * sets current round
	 * @param currentRound
	 */
	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	/**
	 * return last round
	 */
	public int getLastRound() {
		return lastRound;
	}

	/**
	 * sets last round
	 * @param lastRound
	 */
	public void setLastRound(int lastRound) {
		this.lastRound = lastRound;
	}
	
	/**
	 * return true if last round is equal to current round, false otherwise
	 */
	public boolean isSameRound(){
		return lastRound == currentRound;
	}
	
	/* (non-Javadoc)
	 * @see org.freixas.jwizard.JWizardDialog#cancel()
	 */
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

	/**
	 * return the curren operation
	 */
	public int getCurrentOperation() {
		return currentOperation;
	}
	
	/**
	 * sets the current operation
	 * @param currentOperation
	 */
	public void setCurrentOperation(int currentOperation) {
		this.currentOperation = currentOperation;
	}
	
	/**
	 * return the produced constraint as a ConstraintInfo object
	 */
	public ConstraintInfo getProducedConstraint(){
		return constraintProducer.getProducedConstraint();
	}
	
	/**
	 * return the next panel to be shown in the wizard
	 */
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
