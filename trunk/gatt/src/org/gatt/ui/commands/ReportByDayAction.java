package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.DayReportDialog;

/**
 * @author Chucho
 * report by day command
 */
public class ReportByDayAction extends AbstractAction {
	
	/**
	 * frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public ReportByDayAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		OptimizationFacade opFac = OptimizationFacade.getInstance();
		if(!opFac.isOptimizationProcessStarted()){
			JOptionPane.showMessageDialog(frame,
					"Debe haber iniciado un proceso de optimización antes de generar algún reporte",
					"Reporte por día",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		DayReportDialog dialog = new DayReportDialog(frame);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

}
