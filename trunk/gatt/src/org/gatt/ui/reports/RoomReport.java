package org.gatt.ui.reports;

import java.awt.print.PrinterException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import org.gatt.domain.Room;
import org.gatt.domain.Session;

public class RoomReport extends JScrollPane implements Printable {

	private static final long serialVersionUID = 1L;
	private JEditorPane epReportArea = null;
	private Room room;
	private Vector<Session> assignatedSessions;
	
	private Hashtable<String, Integer> hourIndex;
	private Hashtable<Character, Integer> dayIndex;
	private Hashtable<String, Boolean> notApplicable;
	
	private final int NUMBER_OF_DAYS = 6;
	private final int NUMBER_OF_HOURS = 8;
	
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
		
		dayIndex = new Hashtable<Character, Integer>();
		dayIndex.put('L', new Integer(0));
		dayIndex.put('M', new Integer(1));
		dayIndex.put('W', new Integer(2));
		dayIndex.put('J', new Integer(3));
		dayIndex.put('V', new Integer(4));
		dayIndex.put('S', new Integer(5));
		
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
			
			String hourRep = session.getHour().getRepresentation();
			int fIndex = 0;			
			for(int i=0; i < hourRep.length(); i++){
				if(Character.isDigit(hourRep.charAt(i))){
					fIndex = i;
					break;
				}
			}			
			String hour = hourRep.substring(fIndex);
			String days = hourRep.substring(0, fIndex);
			
			System.out.println("days:"+days+" -- hour:"+hour);
			
			if(!notApplicable.containsKey(hour)){
				for(int k=0; k<days.length(); k++){
					schedule[hourIndex.get(hour)][dayIndex.get(days.charAt(k))] = session.getGroup().getSubject().getName();
				}
			}
		}
		return schedule;
	}
	
	private int extractDays(String hourRep){
		
		return 0;
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
			row = "<tr><td style=\"border-collapse:collapse;border-width:1px;border-color:#E4E3DA;border-style:solid;\" align=\"center\" width=\"50px\">"+(i*2+6)+" - "+(i*2+8)+"</td>";
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
