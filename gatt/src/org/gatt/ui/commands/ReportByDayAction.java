package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.DayReportDialog;

public class ReportByDayAction extends AbstractAction {
	
	private GattFrame frame;
	
	public ReportByDayAction(GattFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		OptimizationFacade opFac = OptimizationFacade.getInstance();
		if(!opFac.isOptimizationProcessStarted()){
			JOptionPane.showMessageDialog(frame,
					"Debe haber iniciado un proceso de optimizaci�n antes de generar alg�n reporte",
					"Reporte por d�a",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		DayReportDialog dialog = new DayReportDialog(frame);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

}
