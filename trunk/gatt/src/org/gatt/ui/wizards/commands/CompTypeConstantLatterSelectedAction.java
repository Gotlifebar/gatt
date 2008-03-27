package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.wizards.CreateConditionalComparisonPanel;

/**
 * @author Chucho
 *
 */
public class CompTypeConstantLatterSelectedAction extends AbstractAction {

	/**
	 * the panel in which this command is used
	 */
	private CreateConditionalComparisonPanel panel;
	
	/**
	 * constructor
	 * @param panel
	 */
	public CompTypeConstantLatterSelectedAction(CreateConditionalComparisonPanel panel){
		this.panel = panel;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		panel.getTreeRight1().setEnabled(false);
		panel.getTfConstantValue1().setEnabled(true);

	}

}
