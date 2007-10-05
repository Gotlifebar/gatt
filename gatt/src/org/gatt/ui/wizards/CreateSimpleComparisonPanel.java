package org.gatt.ui.wizards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.freixas.jwizard.JWizardPanel;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.ui.wizards.commands.AddComparisonFormerSimpleComparisonAction;
import org.gatt.ui.wizards.commands.CompTypeAttributeFormerSimpleComparisonSelectedAction;
import org.gatt.ui.wizards.commands.CompTypeConstantFormerSimpleComparisonSelectedAction;
import org.gatt.ui.wizards.commands.DeleteComparisonFormerSimpleComparisonAction;
import org.gatt.ui.wizards.commands.TreeLeftFormerSimpleComparisonSelectionAction;
import org.gatt.ui.wizards.helper.ConstraintWizardInfoWrapper;
import org.gatt.ui.wizards.helper.ConstraintWizardProducer;
import org.gatt.ui.wizards.helper.ListConstraintRepresentation;
import org.gatt.ui.wizards.helper.TreeContentManager;

public class CreateSimpleComparisonPanel extends JWizardPanel{
	
	private static final long serialVersionUID = 1L;
	private JPanel pFormer = null;
	private JScrollPane spLeft = null;
	private JTree treeLeft = null;
	private JLabel lbLeft = null;
	private JComboBox cbOperators = null;
	private JPanel pComparisonType = null;
	private JLabel lbOperator = null;
	private JScrollPane spRight = null;
	private JTree treeRight = null;
	private JLabel lbRight = null;
	private JTextField tfConstantValue = null;
	private JLabel lbConstantValue = null;
	private JScrollPane spComparisons = null;
	private JList listComparisons = null;
	private JLabel lbComparisons = null;
	private JButton bAddComparison = null;
	private JButton bDeleteComparison = null;
	private JPanel pLatter = null;
	private JRadioButton rbAttribute = null;
	private JRadioButton rbConstant = null;
	private JLabel lbComparisonLink = null;
	private JRadioButton rbAnd = null;
	private JRadioButton rbOr = null;
	
	private static final char LEFT_SIDE_VAR = 'i',RIGHT_SIDE_VAR = 'j';
	
	/**
	 * This is the default constructor
	 */
	public CreateSimpleComparisonPanel() {
		super();
		initialize();
		this.setStepTitle("Definir comparición simple");
		setBackStep(-1);
	    setNextStep(3);
	    //if(((ConstraintWizard)getWizardParent()).isEditing())
	    	//loadData();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.getContentPane().setSize(602, 715);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(getPFormer(), null);
	}
	
	private void loadData(){
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		ConstraintWizardInfoWrapper cWrapper = wizard.getConstraintWrapper();

		this.getListComparisons().setModel(cWrapper.getFormerModel());
		
		this.habilitarAndOr();
	}
	
	protected void next(){
		boolean error = false;
		String msg = "Atención:";
			
		//It has to be a singleton, because it's shared through all the panels of the wizard
		//ConstraintWizardProducer constraintProducer = ConstraintWizardProducer.getInstance();
		ConstraintWizardProducer constraintProducer = ((ConstraintWizard)getWizardParent()).getConstraintProducer();
				
		//TODO: Aquí se supone que se crea la constraint con los operandos y operadores que habían definidos.
		
		DefaultListModel listModelFormer = (DefaultListModel)listComparisons.getModel();
		
		if(listModelFormer.isEmpty()){
			error = true;
			msg += "\n-Debe crear al menos una comparación en el antecedente";
		}
		if(error){
			JOptionPane.showMessageDialog(
					this,
					msg,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Antecendente
		
		Enumeration<?> formerComparisons = listModelFormer.elements();
		
		while (formerComparisons.hasMoreElements()) {
			ListConstraintRepresentation elem = (ListConstraintRepresentation) formerComparisons.nextElement();
			
			if(elem.getCType() == null)
				constraintProducer.setComparison(elem.getOperator(),
												elem.getOperand1(), 
												elem.getOperand2(),
												elem.getType());
			else{
				switch(elem.getCType()){
					case OR:
						constraintProducer.addORComparison(elem.getOperator(),
															elem.getOperand1(), 
															elem.getOperand2(),
															elem.getType());
						break;
						
					case AND:
						constraintProducer.addANDComparison(elem.getOperator(),
															elem.getOperand1(), 
															elem.getOperand2(),
															elem.getType());
						break;
				}
			}
		}
		
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		wizard.getConstraintWrapper().setConditional(false);
		wizard.getConstraintWrapper().setFormerModel(listModelFormer);
		
		setNextStep(((ConstraintWizard)getWizardParent()).getNextPanel());
		super.next();
	}
	
	protected void makingVisible(){
		if(((ConstraintWizard)getWizardParent()).isEditing())
	    	loadData();
		this.getWizardParent().setPreferredSize(new Dimension(620,470));
		this.getWizardParent().repaint();
		super.makingVisible();
	}
	
	public void deshabilitarAndOr(){
		rbAnd.setEnabled(false);
		rbOr.setEnabled(false);
		rbAnd.setSelected(false);
		rbOr.setSelected(false);
	}
	
	public void habilitarAndOr(){
		rbAnd.setEnabled(true);
		rbAnd.setSelected(true);
		rbOr.setEnabled(true);
	}
	
	/**
	 * This method initializes pFormer	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPFormer() {
		if (pFormer == null) {
			lbComparisonLink = new JLabel();
			lbComparisonLink.setBounds(new Rectangle(15, 297, 156, 24));
			lbComparisonLink.setText("Enlace de la comparasión:");
			lbComparisons = new JLabel();
			lbComparisons.setBounds(new Rectangle(15, 210, 122, 22));
			lbComparisons.setText("Comparaciones");
			lbConstantValue = new JLabel();
			lbConstantValue.setBounds(new Rectangle(383, 154, 129, 22));
			lbConstantValue.setText("Valor constante");
			lbRight = new JLabel();
			lbRight.setBounds(new Rectangle(383, 24, 178, 25));
			lbRight.setText("Variables lado derecho");
			lbOperator = new JLabel();
			lbOperator.setBounds(new Rectangle(208, 40, 148, 28));
			lbOperator.setText("Operador");
			lbLeft = new JLabel();
			lbLeft.setBounds(new Rectangle(14, 24, 179, 26));
			lbLeft.setText("Variables lado izquierdo");
			pFormer = new JPanel();
			pFormer.setLayout(null);
			pFormer.setBounds(new Rectangle(12, 14, 576, 330));
			pFormer.setBorder(BorderFactory.createTitledBorder(null, "Definición de la restricción", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pFormer.add(getSpLeft(), null);
			pFormer.add(lbLeft, null);
			pFormer.add(getCbOperators(), null);
			pFormer.add(getPComparisonType(), null);
			pFormer.add(lbOperator, null);
			pFormer.add(getSpRight(), null);
			pFormer.add(lbRight, null);
			pFormer.add(getTfConstantValue(), null);
			pFormer.add(lbConstantValue, null);
			pFormer.add(getSpComparisons(), null);
			pFormer.add(lbComparisons, null);
			pFormer.add(getBAddComparison(), null);
			pFormer.add(getBDeleteComparison(), null);
			pFormer.add(lbComparisonLink, null);
			
			ButtonGroup linkGroup = new ButtonGroup();
			linkGroup.add(getRbAnd());
			linkGroup.add(getRbOr());
			pFormer.add(getRbAnd(), null);
			pFormer.add(getRbOr(), null);
		}
		return pFormer;
	}
	
	/**
	 * This method initializes spLeft	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpLeft() {
		if (spLeft == null) {
			spLeft = new JScrollPane();
			spLeft.setBounds(new Rectangle(14, 48, 181, 156));
			spLeft.setViewportView(getTreeLeft());
		}
		return spLeft;
	}

	/**
	 * This method initializes treeLeft	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getTreeLeft() {
		if (treeLeft == null) {
			TreeContentManager manager = new TreeContentManager();
			treeLeft = new JTree(manager.generateAttributesTree(org.gatt.domain.Session.class));
			treeLeft.addTreeSelectionListener(new TreeLeftFormerSimpleComparisonSelectionAction(this));
		}
		return treeLeft;
	}

	/**
	 * This method initializes cbOperators	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbOperators() {
		if (cbOperators == null) {
			cbOperators = new JComboBox();
			cbOperators.setBounds(new Rectangle(208, 69, 155, 26));
			cbOperators.addItem(new String("[Seleccione una opción...]"));
			cbOperators.addItem(DefaultComparisonOperator.EQUAL);
			cbOperators.addItem(DefaultComparisonOperator.LESS_EQUAL_THAN);
			cbOperators.addItem(DefaultComparisonOperator.MORE_EQUAL_THAN);
			cbOperators.addItem(DefaultComparisonOperator.LESS_THAN);
			cbOperators.addItem(DefaultComparisonOperator.MORE_THAN);
			cbOperators.addItem(DefaultComparisonOperator.NOT_EQUAL);
		}
		return cbOperators;
	}

	/**
	 * This method initializes pComparisonType	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPComparisonType() {
		if (pComparisonType == null) {
			pComparisonType = new JPanel();
			pComparisonType.setLayout(null);
			pComparisonType.setBounds(new Rectangle(208, 112, 155, 78));
			pComparisonType.setBorder(BorderFactory.createTitledBorder(null, "Tipo de comparación", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			
			ButtonGroup group = new ButtonGroup();
			group.add(getRbAttribute());
			group.add(getRbConstant());
			
			pComparisonType.add(getRbAttribute(), null);
			pComparisonType.add(getRbConstant(), null);
		}
		return pComparisonType;
	}

	/**
	 * This method initializes spRight	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpRight() {
		if (spRight == null) {
			spRight = new JScrollPane();
			spRight.setLocation(new Point(382, 48));
			spRight.setSize(new Dimension(181, 97));
			spRight.setViewportView(getTreeRight());
		}
		return spRight;
	}

	/**
	 * This method initializes treeRight	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getTreeRight() {
		if (treeRight == null) {
			TreeContentManager manager = new TreeContentManager();
			treeRight = new JTree(manager.generateAttributesTree(org.gatt.domain.Session.class));
			treeRight.setSize(new Dimension(181, 153));
		}
		return treeRight;
	}

	/**
	 * This method initializes tfConstantValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfConstantValue() {
		if (tfConstantValue == null) {
			tfConstantValue = new JTextField();
			tfConstantValue.setBounds(new Rectangle(382, 176, 180, 26));
			tfConstantValue.setEnabled(false);
		}
		return tfConstantValue;
	}

	/**
	 * This method initializes spComparisons	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpComparisons() {
		if (spComparisons == null) {
			spComparisons = new JScrollPane();
			spComparisons.setBounds(new Rectangle(15, 230, 548, 60));
			spComparisons.setViewportView(getListComparisons());
		}
		return spComparisons;
	}

	/**
	 * This method initializes listComparisons	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getListComparisons() {
		if (listComparisons == null) {
			DefaultListModel listModel = new DefaultListModel();
			listComparisons = new JList(listModel);
			listComparisons.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		}
		return listComparisons;
	}

	/**
	 * This method initializes bAddComparison	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBAddComparison() {
		if (bAddComparison == null) {
			bAddComparison = new JButton();
			bAddComparison.setBounds(new Rectangle(389, 297, 81, 22));
			bAddComparison.setText("Agregar");
			bAddComparison.addActionListener(new AddComparisonFormerSimpleComparisonAction(this));
		}
		return bAddComparison;
	}

	/**
	 * This method initializes bDeleteComparison	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBDeleteComparison() {
		if (bDeleteComparison == null) {
			bDeleteComparison = new JButton();
			bDeleteComparison.setText("Eliminar");
			bDeleteComparison.setSize(new Dimension(81, 22));
			bDeleteComparison.setLocation(new Point(481, 297));
			bDeleteComparison.addActionListener(new DeleteComparisonFormerSimpleComparisonAction(this));
		}
		return bDeleteComparison;
	}
	
	/**
	 * This method initializes rbAttribute	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbAttribute() {
		if (rbAttribute == null) {
			rbAttribute = new JRadioButton();
			rbAttribute.setBounds(new Rectangle(14, 20, 126, 21));
			rbAttribute.setText("Atributo");
			rbAttribute.setSelected(true);
			rbAttribute.addActionListener(new CompTypeAttributeFormerSimpleComparisonSelectedAction(this));
		}
		return rbAttribute;
	}

	/**
	 * This method initializes rbConstant	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbConstant() {
		if (rbConstant == null) {
			rbConstant = new JRadioButton();
			rbConstant.setText("Valor constante");
			rbConstant.setLocation(new Point(14, 47));
			rbConstant.setSize(new Dimension(126, 21));
			rbConstant.addActionListener(new CompTypeConstantFormerSimpleComparisonSelectedAction(this));
		}
		return rbConstant;
	}
	
	/**
	 * This method initializes rbAnd	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbAnd() {
		if (rbAnd == null) {
			rbAnd = new JRadioButton();
			rbAnd.setBounds(new Rectangle(178, 298, 69, 21));
			rbAnd.setText("Y (AND)");
			rbAnd.setEnabled(false);
		}
		return rbAnd;
	}

	/**
	 * This method initializes rbOr	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbOr() {
		if (rbOr == null) {
			rbOr = new JRadioButton();
			rbOr.setText("O (OR)");
			rbOr.setLocation(new Point(253, 297));
			rbOr.setSize(new Dimension(67, 24));
			rbOr.setEnabled(false);
		}
		return rbOr;
	}
}
