package org.gatt.ui.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.gatt.ui.dialogs.commands.AcceptDayReportAction;
import org.gatt.ui.dialogs.helper.DayWrapper;

public class DayReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel pDays = null;
	private JComboBox cbDays = null;
	private JButton bOk = null;

	/**
	 * @param owner
	 */
	public DayReportDialog(Frame owner) {
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
		this.setTitle("Reporte por día");
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
			jContentPane.add(getPDays(), null);
			jContentPane.add(getBOk(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pDays	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPDays() {
		if (pDays == null) {
			pDays = new JPanel();
			pDays.setLayout(null);
			pDays.setSize(new Dimension(172, 77));
			pDays.setBorder(BorderFactory.createTitledBorder(null, "Seleccione un día", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pDays.setLocation(new Point(9, 15));
			pDays.add(getCbDays(), null);
		}
		return pDays;
	}

	/**
	 * This method initializes cbDays	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbDays() {
		if (cbDays == null) {
			Vector<DayWrapper> days = new Vector<DayWrapper>();
			for(int i=1; i<=6; i++){
				days.add(new DayWrapper(i));
			}
			cbDays = new JComboBox(new DefaultComboBoxModel(days));
			cbDays.setSize(new Dimension(145, 27));
			cbDays.setLocation(new Point(15, 29));
		}
		return cbDays;
	}

	/**
	 * This method initializes bOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBOk() {
		if (bOk == null) {
			bOk = new JButton();
			bOk.setSize(new Dimension(82, 23));
			bOk.setText("Aceptar");
			bOk.setLocation(new Point(55, 101));
			bOk.addActionListener(new AcceptDayReportAction(this));
		}
		return bOk;
	}

}
