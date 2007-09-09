package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.ui.GattFrame;
import org.gatt.ui.dialogs.RoomReportDialog;

public class ReportByRoomAction extends AbstractAction {

	private GattFrame frame;
	
	public ReportByRoomAction(GattFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		RoomReportDialog dialog = new RoomReportDialog(frame);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

}
