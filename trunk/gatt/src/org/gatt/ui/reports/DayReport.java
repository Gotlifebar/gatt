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

/**
 * @author Chucho
 * Report by day
 */
public class DayReport extends JScrollPane implements Printable {

	private static final long serialVersionUID = 1L;
	private JEditorPane epReportArea = null;
	/**
	 * The sessions that will appear in the report
	 */
	private Vector<Session> assignatedSessions;
	
	private Hashtable<String, Integer> hourIndex;
	private Hashtable<String, Boolean> notApplicable;
	
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
		notApplicable = new Hashtable<String, Boolean>();
		notApplicable.put("7-10", true);
		notApplicable.put("6-9", true);
		notApplicable.put("10-13", true);
		notApplicable.put("13-16", true);
		
		hourIndex = new Hashtable<String, Integer>();
		hourIndex.put("6-8", new Integer(0));
		hourIndex.put("8-10", new Integer(1));
		hourIndex.put("10-12", new Integer(2));
		hourIndex.put("12-14", new Integer(3));
		hourIndex.put("14-16", new Integer(4));
		hourIndex.put("16-18", new Integer(5));
		hourIndex.put("18-20", new Integer(6));
		hourIndex.put("20-22", new Integer(7));
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
	
	/**
	 * convert session to a hashtable a returns it
	 */
	private Hashtable<String,String[]> convertSessionsToHashtable(){
		
		Hashtable<String,String[]> roomsHash = new Hashtable<String,String[]>();
		
		for (Iterator<Session> iterator = assignatedSessions.iterator(); iterator.hasNext();) {
			Session session = iterator.next();
			String space = session.getRoom().getNumber();
			String hourRep = session.getHour().getRepresentation();
			int fIndex = 0;			
			for(int i=0; i < hourRep.length(); i++){
				if(Character.isDigit(hourRep.charAt(i))){
					fIndex = i;
					break;
				}
			}			
			String hour = hourRep.substring(fIndex);

			if(!roomsHash.containsKey(space) && !notApplicable.containsKey(hour)){
				roomsHash.put(space, new String[NUMBER_OF_HOURS]);
				roomsHash.get(space)[hourIndex.get(hour)] = session.getGroup().getSubject().getName() + " G " + session.getGroup().getNumber();
			}
		}
		return roomsHash;
	}
	
	/*private int extractHourIndex(String hourRep){
		
System.out.println("Tratando de encontrar: " + hour);
		return hourIndex.get(hour);
	}*/
	
	/**
	 * generates de report as a string
	 */
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
	
	/* (non-Javadoc)
	 * @see org.gatt.ui.reports.Printable#print()
	 */
	public void print(){
		try{
			getEpReportArea().print();
		}catch(PrinterException pEx){
			pEx.printStackTrace();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
