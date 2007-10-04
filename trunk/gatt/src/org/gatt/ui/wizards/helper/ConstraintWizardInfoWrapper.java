package org.gatt.ui.wizards.helper;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.DefaultListModel;

import org.gatt.constraint.ConstraintInfo;

public class ConstraintWizardInfoWrapper implements Serializable {
	
	private DefaultListModel formerModel;
	private DefaultListModel latterModel;
	private boolean conditional;
	private ConstraintInfo constraintInfo;
		
	public void setFormerModel(DefaultListModel formerModel) {
		this.formerModel = formerModel;
	}

	public void setLatterModel(DefaultListModel latterModel) {
		this.latterModel = latterModel;
	}

	public void setConditional(boolean conditional) {
		this.conditional = conditional;
	}

	public void setConstraintInfo(ConstraintInfo constraintInfo) {
		this.constraintInfo = constraintInfo;
	}

	public DefaultListModel getFormerModel() {
		return formerModel;
	}

	public DefaultListModel getLatterModel() {
		return latterModel;
	}

	public boolean isConditional() {
		return conditional;
	}

	public ConstraintInfo getConstraintInfo() {
		return constraintInfo;
	}
}
