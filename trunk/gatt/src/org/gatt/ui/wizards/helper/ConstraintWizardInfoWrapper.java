package org.gatt.ui.wizards.helper;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.DefaultListModel;

import org.gatt.constraint.ConstraintInfo;

/**
 * @author Chucho
 * wraps the information recollected in the constraints wizard
 */
public class ConstraintWizardInfoWrapper implements Serializable {
	
	/**
	 * The model of a list in a panel
	 */
	private DefaultListModel formerModel;
	/**
	 * The model of a list in a panel
	 */
	private DefaultListModel latterModel;
	/**
	 * indicates if the constraint is conditional
	 */
	private boolean conditional;
	/**
	 * ConstraintInfo Object
	 */
	private ConstraintInfo constraintInfo;
		
	/**
	 * sets the former list model
	 * @param formerModel
	 */
	public void setFormerModel(DefaultListModel formerModel) {
		this.formerModel = formerModel;
	}

	/**
	 * sets the latter list model
	 * @param latterModel
	 */
	public void setLatterModel(DefaultListModel latterModel) {
		this.latterModel = latterModel;
	}

	/**
	 * sets if the constraint is conditional
	 * @param conditional
	 */
	public void setConditional(boolean conditional) {
		this.conditional = conditional;
	}

	/**
	 * sets the ConstraintInfo object
	 * @param constraintInfo
	 */
	public void setConstraintInfo(ConstraintInfo constraintInfo) {
		this.constraintInfo = constraintInfo;
	}

	/**
	 * return the former list model
	 */
	public DefaultListModel getFormerModel() {
		return formerModel;
	}

	/**
	 * return the latter list model
	 */
	public DefaultListModel getLatterModel() {
		return latterModel;
	}

	/**
	 * return whether the constraint is condition or not
	 */
	public boolean isConditional() {
		return conditional;
	}

	/**
	 * return the ConstraintInfo object
	 */
	public ConstraintInfo getConstraintInfo() {
		return constraintInfo;
	}
}
