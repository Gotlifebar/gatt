package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.gatt.ui.GattFrame;
import org.gatt.ui.reports.RoomReport;

public class PrintAction extends AbstractAction {

	private GattFrame frame;
	
	public PrintAction(GattFrame frame){
		this.frame = frame;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		JTabbedPane tPane = frame.getTpTabs();
		
		if(tPane.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(frame,
					"Debe seleccionar una pestaña para poder imprimir",
					"Imprimir",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		RoomReport report = (RoomReport)tPane.getSelectedComponent();
		report.print();
	}

}
