package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.gatt.ui.GattFrame;
import org.gatt.ui.reports.Printable;

/**
 * @author Chucho
 * print command
 */
public class PrintAction extends AbstractAction {

	/**
	 * frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public PrintAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JTabbedPane tPane = frame.getTpTabs();
		
		if(tPane.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(frame,
					"Debe seleccionar una pestaña para poder imprimir",
					"Imprimir",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Printable report = (Printable)tPane.getSelectedComponent();
		report.print();
	}

}
