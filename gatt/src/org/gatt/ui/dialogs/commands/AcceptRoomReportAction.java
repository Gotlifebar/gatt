package org.gatt.ui.dialogs.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.RoomReportDialog;
import org.gatt.ui.dialogs.helper.RoomWrapper;
import org.gatt.ui.reports.RoomReport;

public class AcceptRoomReportAction extends AbstractAction {
	
	private RoomReportDialog dialog;
	
	public AcceptRoomReportAction(RoomReportDialog dialog){
		this.dialog = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GattFrame mainFrame = GattFrame.getInstance();
		RoomWrapper rw = (RoomWrapper)dialog.getCbRoom().getSelectedItem();
		mainFrame.addReport("Asignación aula " + rw.getRoom().getSpace(), new RoomReport(rw.getRoom()));
		dialog.dispose();
	}

}
