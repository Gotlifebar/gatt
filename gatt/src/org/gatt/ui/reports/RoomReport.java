package org.gatt.ui.reports;

import java.awt.print.PrinterException;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import org.gatt.domain.Room;

public class RoomReport extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JEditorPane epReportArea = null;
	private Room room;

	/**
	 * This is the default constructor
	 */
	public RoomReport(Room room) {
		super();
		this.room = room;
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
