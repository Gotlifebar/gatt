package org.gatt.ui.reports;

import java.awt.print.PrinterException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import org.gatt.domain.Hour;
import org.gatt.domain.Session;
import org.gatt.domain.factories.DomainObjectFactoryFacade;

public class DayReport extends JScrollPane implements Printable {

	private static final long serialVersionUID = 1L;
	private JEditorPane epReportArea = null;
	private Vector<Session> assignatedSessions;
	
	private final int NUMBER_OF_HOURS = 8;
	
	/**
	 * This is the default constructor
	 */
	public DayReport(Vector<Session> assignatedSessions) {
		super();
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
	
	private Hashtable<String,String[]> convertSessionsToHashtable(){
		
		Hashtable<String,String[]> roomsHash = new Hashtable<String,String[]>();
		
		for (Iterator<Session> iterator = assignatedSessions.iterator(); iterator.hasNext();) {
			Session session = iterator.next();
			String space = session.getRoom().getSpace();
			if(!roomsHash.containsKey(space))
				roomsHash.put(space, new String[NUMBER_OF_HOURS]);
			roomsHash.get(space)[((session.getHour().getTime()-4)/2)-1] = session.getGroup().getSubject().getName() + " G " + session.getGroup().getNumber();	
		}
		return roomsHash;
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
					"<br/><br/><br/>"+
					"<table style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" width=\"80%\" align=\"center\">"+
					  "<tr>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" width=\"50px\" align=\"center\"><b>AULA</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>6-8</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>8-10</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>10-12</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>12-14</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>14-16</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>16-18</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>18-20</b></td>"+
					    "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\"><b>20-22</b></td>"+
					  "</tr>";
		
		Hashtable<String,String[]> rooms = convertSessionsToHashtable();
		String row;
		Enumeration<String> keys = rooms.keys();
		while (keys.hasMoreElements()) {
			String elem = keys.nextElement();
			String roomSchedule[] = rooms.get(elem);
			row = "<tr><td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\" width=\"50px\">"+elem+"</td>";
			for (int i = 0; i < NUMBER_OF_HOURS; i++) {
				if(roomSchedule[i] == null)
					row += "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\">&nbsp;</td>";
				else
					row += "<td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\">"+roomSchedule[i]+"</td>";
			}
			row += "</tr>";
			html += row;
		}
		
		html +=	"</table>"+
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