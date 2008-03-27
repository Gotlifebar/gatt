package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;

import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.optimization.util.NumericTransformationFunction;
import org.gatt.optimization.util.SolutionManager;
import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.DayReportDialog;
import org.gatt.ui.dialogs.helper.DayWrapper;
import org.gatt.ui.reports.DayReport;

/**
 * @author Chucho
 * Accept command in Day report dialog
 */
public class AcceptDayReportAction extends AbstractAction {
	
	/**
	 * the dialog
	 */
	private DayReportDialog dialog;
	
	/**
	 * constructor
	 * @param dialog
	 */
	public AcceptDayReportAction(DayReportDialog dialog){
		this.dialog = dialog;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		GattFrame mainFrame = GattFrame.getInstance();
		DayWrapper dw = (DayWrapper)dialog.getCbDays().getSelectedItem();
		int roomsCount = DomainObjectFactoryFacade.getInstance().getRoomsCount(),
			hoursCount = DomainObjectFactoryFacade.getInstance().getHoursCount();
		SolutionManager solManager = new SolutionManager(new NumericTransformationFunction(roomsCount, hoursCount));
		Vector<Session> filtered = solManager.filterSolutionByDay(OptimizationFacade.getInstance().getBestSolution(), dw.getDay());
		mainFrame.addReport("Asignación del día " + dw.toString(), new DayReport(filtered));
		dialog.setVisible(false);
		dialog.dispose();
	}

}
