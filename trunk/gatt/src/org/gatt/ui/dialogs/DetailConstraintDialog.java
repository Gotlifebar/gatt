package org.gatt.ui.dialogs;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.ui.dialogs.commands.CloseDetailConstraintDialogAction;

public class DetailConstraintDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbId = null;
	private JLabel lbName = null;
	private JLabel lbDescription = null;
	private JLabel lbSignificance = null;
	private JLabel lbDefinition = null;
	private JTextField tfId = null;
	private JTextField tfName = null;
	private JTextField tfSignificance = null;
	private JScrollPane spDescription = null;
	private JTextArea taDescription = null;
	private JScrollPane spDefinition = null;
	private JTextArea taDefinition = null;
	private JButton bClose = null;
	private JPanel pSeparator = null;
	
	private ConstraintInfo constraint;
	
	/**
	 * @param owner
	 */
	public DetailConstraintDialog(Frame owner, ConstraintInfo constraint) {
		super(owner);
		this.constraint = constraint;
		initialize();
	}
	
	
	public DetailConstraintDialog(Dialog owner, ConstraintInfo constraint) {
		super(owner);
		this.constraint = constraint;
		initialize();
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(381, 406);
		this.setResizable(false);
		this.setTitle("Detalles de la restricción");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lbDefinition = new JLabel();
			lbDefinition.setText("Definición:");
			lbDefinition.setLocation(new Point(12, 221));
			lbDefinition.setSize(new Dimension(100, 25));
			lbDefinition.setName("lbId");
			lbSignificance = new JLabel();
			lbSignificance.setText("Significancia:");
			lbSignificance.setLocation(new Point(12, 189));
			lbSignificance.setSize(new Dimension(100, 25));
			lbSignificance.setName("lbId");
			lbDescription = new JLabel();
			lbDescription.setText("Descripción:");
			lbDescription.setLocation(new Point(12, 82));
			lbDescription.setSize(new Dimension(100, 25));
			lbDescription.setName("lbId");
			lbName = new JLabel();
			lbName.setText("Nombre:");
			lbName.setLocation(new Point(12, 50));
			lbName.setSize(new Dimension(100, 25));
			lbName.setName("lbId");
			lbId = new JLabel();
			lbId.setText("ID:");
			lbId.setLocation(new Point(12, 18));
			lbId.setSize(new Dimension(100, 25));
			lbId.setName("lbId");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lbId, null);
			jContentPane.add(lbName, null);
			jContentPane.add(lbDescription, null);
			jContentPane.add(lbSignificance, null);
			jContentPane.add(lbDefinition, null);
			jContentPane.add(getTfId(), null);
			jContentPane.add(getTfName(), null);
			jContentPane.add(getTfSignificance(), null);
			jContentPane.add(getSpDescription(), null);
			jContentPane.add(getSpDefinition(), null);
			jContentPane.add(getBClose(), null);
			jContentPane.add(getPSeparator(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tfId	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setLocation(new Point(120, 18));
			tfId.setEditable(false);
			tfId.setSize(new Dimension(247, 25));
			tfId.setText(constraint.getId());
		}
		return tfId;
	}

	/**
	 * This method initializes tfName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setLocation(new Point(120, 50));
			tfName.setEditable(false);
			tfName.setSize(new Dimension(247, 25));
			tfName.setText(constraint.getName());
		}
		return tfName;
	}

	/**
	 * This method initializes tfSignificance	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfSignificance() {
		if (tfSignificance == null) {
			tfSignificance = new JTextField();
			tfSignificance.setLocation(new Point(120, 189));
			tfSignificance.setEditable(false);
			tfSignificance.setSize(new Dimension(247, 25));
			tfSignificance.setText(Double.toString(constraint.getSignificance()));
		}
		return tfSignificance;
	}

	/**
	 * This method initializes spDescription	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpDescription() {
		if (spDescription == null) {
			spDescription = new JScrollPane();
			spDescription.setLocation(new Point(120, 82));
			spDescription.setViewportView(getTaDescription());
			spDescription.setSize(new Dimension(247, 99));
		}
		return spDescription;
	}

	/**
	 * This method initializes taDescription	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTaDescription() {
		if (taDescription == null) {
			taDescription = new JTextArea();
			taDescription.setEditable(false);
			taDescription.setLineWrap(true);
			taDescription.setText(constraint.getDescription());
		}
		return taDescription;
	}

	/**
	 * This method initializes spDefinition	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpDefinition() {
		if (spDefinition == null) {
			spDefinition = new JScrollPane();
			spDefinition.setLocation(new Point(120, 221));
			spDefinition.setViewportView(getTaDefinition());
			spDefinition.setSize(new Dimension(247, 96));
		}
		return spDefinition;
	}

	/**
	 * This method initializes taDefinition	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTaDefinition() {
		if (taDefinition == null) {
			taDefinition = new JTextArea();
			taDefinition.setEditable(false);
			taDefinition.setLineWrap(true);
			taDefinition.setText(constraint.getDefinition());
		}
		return taDefinition;
	}

	/**
	 * This method initializes bClose	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBClose() {
		if (bClose == null) {
			bClose = new JButton();
			bClose.setBounds(new Rectangle(138, 338, 95, 26));
			bClose.setText("Cerrar");
			bClose.addActionListener(new CloseDetailConstraintDialogAction(this));
		}
		return bClose;
	}

	/**
	 * This method initializes pSeparator	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPSeparator() {
		if (pSeparator == null) {
			pSeparator = new JPanel();
			pSeparator.setLayout(new GridBagLayout());
			pSeparator.setLocation(new Point(0, 328));
			pSeparator.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			pSeparator.setSize(new Dimension(373, 1));
		}
		return pSeparator;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
