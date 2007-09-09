package org.gatt.ui.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.gatt.domain.Room;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.ui.dialogs.commands.AcceptRoomReportAction;
import org.gatt.ui.dialogs.helper.RoomWrapper;

public class RoomReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox cbRoom = null;
	private JPanel pRoomSelection = null;
	private JButton bOk = null;
	/**
	 * @param owner
	 */
	public RoomReportDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(197, 168);
		this.setTitle("Reporte por aula");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPRoomSelection(), null);
			jContentPane.add(getBOk(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cbRoom	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbRoom() {
		if (cbRoom == null) {
			DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
			try{
				doff.setDAOFactoryClass("org.gatt.domain.factories.mysqldaofactory.MySqlDAOFactory");
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			Collection<Room> rooms = doff.getAllRooms();
			Vector<RoomWrapper> roomWrappers = new Vector<RoomWrapper>();
			
			for (Iterator iterator = rooms.iterator(); iterator.hasNext();) {
				Room room = (Room) iterator.next();
				roomWrappers.addElement(new RoomWrapper(room));
			}
			cbRoom = new JComboBox(new DefaultComboBoxModel(roomWrappers));
			cbRoom.setBounds(new Rectangle(15, 29, 145, 27));
		}
		return cbRoom;
	}

	/**
	 * This method initializes pRoomSelection	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPRoomSelection() {
		if (pRoomSelection == null) {
			pRoomSelection = new JPanel();
			pRoomSelection.setLayout(null);
			pRoomSelection.setBounds(new Rectangle(9, 15, 172, 77));
			pRoomSelection.setBorder(BorderFactory.createTitledBorder(null, "Seleccione un aula", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pRoomSelection.add(getCbRoom(), null);
		}
		return pRoomSelection;
	}

	/**
	 * This method initializes bOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBOk() {
		if (bOk == null) {
			bOk = new JButton();
			bOk.setBounds(new Rectangle(55, 101, 82, 23));
			bOk.setText("Aceptar");
			bOk.addActionListener(new AcceptRoomReportAction(this));
		}
		return bOk;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
