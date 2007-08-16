package org.gatt.ui.wizards;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.freixas.jwizard.JWizardPanel;

public class AddConstraintPanel extends JWizardPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelConstraintType = null;
	private JComboBox comboConstraintTypes = null;
	private JPanel pImportance = null;
	private JRadioButton rbVeryLow = null;
	private JRadioButton rbLow = null;
	private JRadioButton rbMedium = null;
	private JRadioButton rbHigh = null;
	private JRadioButton rbVeryHigh = null;
	private JLabel labelImportance = null;
	private JLabel labelName = null;
	private JLabel labelDescription = null;
	private JTextField tfName = null;
	private JScrollPane spDescription = null;
	private JTextArea tsDescription = null;
	/**
	 * This is the default constructor
	 */
	public AddConstraintPanel() {
		super();
		this.setStepTitle("Agregar Restricción");
		initialize();
		setBackStep(-1);
	    setNextStep(1);
	}
	
	protected void next(){
		boolean error = false;
		String msg = "Atención:";
		if(getTfName().getText().equals("")){
			error = true;
			msg += "\n" + "- Debe ingresar un nombre para la restricción.";
		}
		
		if(getComboConstraintTypes().getSelectedIndex() == 0){
			error = true;
			msg += "\n" + "- Debe seleccionar un tipo de restricción.";
		}
			
		
		if(error){
			JOptionPane.showMessageDialog(
						this, 
						msg,
						"Error",
						JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Cambiar por la fachada
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		if(this.getComboConstraintTypes().getSelectedIndex() == 1){
			wizard.setCType(ConstraintWizard.ConstraintType.CONDITIONAL);			
		}else{
			wizard.setCType(ConstraintWizard.ConstraintType.SIMPLE);
		}
		wizard.getConstraintProducer().setConstraintType(this.getComboConstraintTypes().getSelectedIndex());
		wizard.getConstraintProducer().setConstraintName(getTfName().getText());
		wizard.getConstraintProducer().setConstraintDescription(getTsDescription().getText());
		
		if(getRbVeryLow().isSelected())
			wizard.getConstraintProducer().setConstraintSignificance(0.19d);
		if(getRbLow().isSelected())
			wizard.getConstraintProducer().setConstraintSignificance(0.39d);
		if(getRbMedium().isSelected())
			wizard.getConstraintProducer().setConstraintSignificance(0.59d);
		if(getRbHigh().isSelected())
			wizard.getConstraintProducer().setConstraintSignificance(0.79d);
		if(getRbVeryHigh().isSelected())
			wizard.getConstraintProducer().setConstraintSignificance(0.99d);

		this.setNextStep(wizard.getNextPanel());
		super.next();
		return;
		
	}
	
	protected void makingVisible(){
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		if(!wizard.isSameRound() && wizard.getCurrentRound() != 1){
			resetView();
		}
		super.makingVisible();
	}
	
	private void resetView(){
		this.getComboConstraintTypes().setSelectedIndex(0);
		this.getRbMedium().setSelected(true);
		this.getTfName().setText("");
		this.getTsDescription().setText("");
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		labelDescription = new JLabel();
		labelDescription.setText("Descripción:");
		labelDescription.setSize(new Dimension(153, 31));
		labelDescription.setLocation(new Point(14, 59));
		labelName = new JLabel();
		labelName.setText("Nombre de la restricción:");
		labelName.setLocation(new Point(14, 15));
		labelName.setSize(new Dimension(153, 31));
		labelImportance = new JLabel();
		labelImportance.setText("Importancia:");
		labelImportance.setSize(new Dimension(153, 30));
		labelImportance.setLocation(new Point(13, 199));
		labelConstraintType = new JLabel();
		labelConstraintType.setText("Tipo de restricción:");
		labelConstraintType.setLocation(new Point(13, 154));
		labelConstraintType.setSize(new Dimension(153, 31));
		this.setSize(602, 356);
		this.getContentPane().setSize(602, 356);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(labelConstraintType, null);
		this.getContentPane().add(getComboConstraintTypes(), null);
		this.getContentPane().add(getPImportance(), null);
		this.getContentPane().add(labelImportance, null);
		this.getContentPane().add(labelName, null);
		this.getContentPane().add(labelDescription, null);
		this.getContentPane().add(getTfName(), null);
		this.getContentPane().add(getSpDescription(), null);
	}

	/**
	 * This method initializes comboConstraintTypes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboConstraintTypes() {
		if (comboConstraintTypes == null) {
			comboConstraintTypes = new JComboBox();
			comboConstraintTypes.setBounds(new Rectangle(178, 154, 410, 32));
			comboConstraintTypes.addItem(new String("[Seleccione un opción...]"));
			comboConstraintTypes.addItem(new String("Restricción de tipo Si ... Entonces"));
			comboConstraintTypes.addItem(new String("Restricción simple"));
		}
		return comboConstraintTypes;
	}

	/**
	 * This method initializes pImportance	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPImportance() {
		if (pImportance == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(5);
			gridLayout.setColumns(1);
			pImportance = new JPanel();
			pImportance.setSize(new Dimension(409, 134));
			pImportance.setLocation(new Point(179, 199));
			pImportance.setLayout(gridLayout);
			pImportance.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			
			ButtonGroup group = new ButtonGroup();
			group.add(getRbVeryLow());
			group.add(getRbLow());
			group.add(getRbMedium());
			group.add(getRbHigh());
			group.add(getRbVeryHigh());
			
			pImportance.add(getRbVeryLow(), null);
			pImportance.add(getRbLow(), null);
			pImportance.add(getRbMedium(), null);
			pImportance.add(getRbHigh(), null);
			pImportance.add(getRbVeryHigh(), null);
		}
		return pImportance;
	}

	/**
	 * This method initializes rbVeryLow	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbVeryLow() {
		if (rbVeryLow == null) {
			rbVeryLow = new JRadioButton();
			rbVeryLow.setText("Muy baja");
		}
		return rbVeryLow;
	}

	/**
	 * This method initializes rbLow	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbLow() {
		if (rbLow == null) {
			rbLow = new JRadioButton();
			rbLow.setText("Baja");
		}
		return rbLow;
	}

	/**
	 * This method initializes rbMedium	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbMedium() {
		if (rbMedium == null) {
			rbMedium = new JRadioButton();
			rbMedium.setSelected(true);
			rbMedium.setText("Media");
		}
		return rbMedium;
	}

	/**
	 * This method initializes rbHigh	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbHigh() {
		if (rbHigh == null) {
			rbHigh = new JRadioButton();
			rbHigh.setText("Alta");
		}
		return rbHigh;
	}

	/**
	 * This method initializes rbVeryHigh	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbVeryHigh() {
		if (rbVeryHigh == null) {
			rbVeryHigh = new JRadioButton();
			rbVeryHigh.setToolTipText("");
			rbVeryHigh.setText("Muy alta");
		}
		return rbVeryHigh;
	}

	/**
	 * This method initializes tfName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setLocation(new Point(178, 15));
			tfName.setSize(new Dimension(410, 32));
		}
		return tfName;
	}

	/**
	 * This method initializes spDescription	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpDescription() {
		if (spDescription == null) {
			spDescription = new JScrollPane();
			spDescription.setSize(new Dimension(410, 82));
			spDescription.setViewportView(getTsDescription());
			spDescription.setLocation(new Point(178, 59));
		}
		return spDescription;
	}

	/**
	 * This method initializes tsDescription	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTsDescription() {
		if (tsDescription == null) {
			tsDescription = new JTextArea();
		}
		return tsDescription;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
