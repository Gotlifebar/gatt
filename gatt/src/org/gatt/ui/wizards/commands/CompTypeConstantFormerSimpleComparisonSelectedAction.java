package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateSimpleComparisonPanel;

/**
 * @author Chucho
 *
 */
public class CompTypeConstantFormerSimpleComparisonSelectedAction extends AbstractAction {

	/**
	 * the panel in which this command is used 
	 */
	private CreateSimpleComparisonPanel panel;
	
	/**
	 * constructor
	 * @param panel
	 */
	public CompTypeConstantFormerSimpleComparisonSelectedAction(CreateSimpleComparisonPanel panel){
		this.panel = panel;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight().setEnabled(false);
		panel.getTfConstantValue().setEnabled(true);

	}

}
