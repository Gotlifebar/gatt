package org.gatt.ui.reports;

import java.awt.print.PrinterException;
import java.util.Iterator;
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
	
	private final int NUMBER_OF_DAYS = 6;
	private final int NUMBER_OF_HOURS = 12;
	
	/**
	 * This is the default constructor
	 */
	public RoomReport(Room room, Vector<Session> assignatedSessions) {
		super();
		this.room = room;
		this.assignatedSessions = assignatedSessions;
		initialize();
		generateReport();
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
			epReportArea.setText(generateReport());
			epReportArea.setEditable(false);
		}
		return epReportArea;
	}
	
	private String[][] convertSessionsToArray(){
		
		String[][] schedule = new String[NUMBER_OF_HOURS][NUMBER_OF_DAYS];
		
		for (Iterator<Session> iterator = assignatedSessions.iterator(); iterator.hasNext();) {
			Session session = iterator.next();
			schedule[session.getHour().getTime()/2][session.getHour().getDay()-1] = session.getGroup().getSubject().getName();			
		}
		return schedule;
	}
	
	private String generateReport(){
		String html = "<html>" +
					"<head>" +
					"<style type=\"text/css\">"+
					"body {" +
					"font-family:\"Trebuchet MS\";"+
					"font-weight:normal;" +
					"font-size:12pt;" +
					"color:#000000;" +
					"};" +
					"</style>"+
					"</head>"+
					"<body>"+
					"<br/><br/><br/>" +
					"<table style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" width=\"80%\" align=\"center\">"+
					  "<tr>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" width=\"50px\" align=\"center\"><b>HORA</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>LUNES</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>M&Aacute;RTES</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>MI&Eacute;RCOLES</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>JUEVES</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>VIERNES</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>S&Aacute;BADO</b></td>"+
					  "</tr>";
		
		String schedule[][] = convertSessionsToArray();
		
		String row;
		for (int i = 0; i < NUMBER_OF_HOURS; i++) {
			row = "<tr><td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\" width=\"50px\">"+i*2+" - "+(i*2+2)+"</td>";
			for(int j = 0; j < NUMBER_OF_DAYS; j++){
				if(schedule[i][j] == null)
					row += "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\">&nbsp;</td>";
				else
					row += "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\">"+schedule[i][j]+"</td>";
			}
			row += "</tr>";
			html += row;
		}
		html += "</table>"+
				"</body>"+
				"</html>";
		return html;
					
	}
	
	public void print(){
		try{
			getEpReportArea().print();
		}catch(PrinterException pEx){
			pEx.printStackTrace();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
