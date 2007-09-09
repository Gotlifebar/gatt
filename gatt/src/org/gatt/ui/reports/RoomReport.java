package org.gatt.ui.reports;

import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import org.gatt.domain.Room;
import org.gatt.domain.Session;

public class RoomReport extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JEditorPane epReportArea = null;
	private Room room;
	private Vector<Session> assignatedSessions;

	/**
	 * This is the default constructor
	 */
	public RoomReport(Room room, Vector<Session> assignatedSessions) {
		super();
		this.room = room;
		this.assignatedSessions = assignatedSessions;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(516, 313);

		this.setViewportView(getEpReportArea());
	}

	/**
	 * This method initializes epReportArea	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getEpReportArea() {
		if (epReportArea == null) {
			epReportArea = new JEditorPane();
			epReportArea.setContentType("text/html");
			epReportArea.setEditable(false);
		}
		return epReportArea;
	}
	
	public void print(){
		try{
			getEpReportArea().print();
		}catch(PrinterException pEx){
			pEx.printStackTrace();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
