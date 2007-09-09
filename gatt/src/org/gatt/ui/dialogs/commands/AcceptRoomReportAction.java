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
import org.gatt.ui.dialogs.RoomReportDialog;
import org.gatt.ui.dialogs.helper.RoomWrapper;
import org.gatt.ui.reports.RoomReport;

public class AcceptRoomReportAction extends AbstractAction {
	
	private RoomReportDialog dialog;
	
	public AcceptRoomReportAction(RoomReportDialog dialog){
		this.dialog = dialog;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		GattFrame mainFrame = GattFrame.getInstance();
		RoomWrapper rw = (RoomWrapper)dialog.getCbRoom().getSelectedItem();
		int roomsCount = DomainObjectFactoryFacade.getInstance().getRoomsCount(),
			hoursCount = DomainObjectFactoryFacade.getInstance().getHoursCount();
		SolutionManager solManager = new SolutionManager(new NumericTransformationFunction(roomsCount, hoursCount));
		Vector<Session> filtered = solManager.filterSolutionBy(OptimizationFacade.getInstance().getBestSolution(), rw.getRoom());
		mainFrame.addReport("Asignación aula " + rw.getRoom().getSpace(), new RoomReport(rw.getRoom(), filtered));
		dialog.dispose();
	}

}
