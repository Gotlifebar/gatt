package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;

import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.optimization.ttga.SolutionManager;
import org.gatt.optimization.util.NumericTransformationFunction;
import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.DayReportDialog;
import org.gatt.ui.dialogs.helper.DayWrapper;
import org.gatt.ui.reports.DayReport;

public class AcceptDayReportAction extends AbstractAction {
	
	private DayReportDialog dialog;
	
	public AcceptDayReportAction(DayReportDialog dialog){
		this.dialog = dialog;
	}
	
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
