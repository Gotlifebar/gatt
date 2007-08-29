package org.gatt.ui.wizards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Iterator;

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
import org.gatt.ui.wizards.commands.AddComparisonFormerAction;
import org.gatt.ui.wizards.commands.AddComparisonLatterAction;
import org.gatt.ui.wizards.commands.CompTypeAttributeFormerSelectedAction;
import org.gatt.ui.wizards.commands.CompTypeAttributeLatterSelectedAction;
import org.gatt.ui.wizards.commands.CompTypeConstantFormerSelectedAction;
import org.gatt.ui.wizards.commands.CompTypeConstantLatterSelectedAction;
import org.gatt.ui.wizards.commands.DeleteComparisonFormerAction;
import org.gatt.ui.wizards.commands.DeleteComparisonLatterAction;
import org.gatt.ui.wizards.commands.TreeLeftFormerSelectionAction;
import org.gatt.ui.wizards.commands.TreeLeftLatterSelectionAction;
import org.gatt.ui.wizards.helper.ConstraintWizardProducer;
import org.gatt.ui.wizards.helper.ListConstraintRepresentation;
import org.gatt.ui.wizards.helper.TreeContentManager;

public class CreateConditionalComparisonPanel extends JWizardPanel {

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
	private JScrollPane spLeft1 = null;
	private JTree treeLeft1 = null;
	private JLabel lbLeft1 = null;
	private JComboBox cbOperators1 = null;
	private JPanel pComparisonType1 = null;
	private JLabel lbOperator1 = null;
	private JScrollPane spRight1 = null;
	private JTree treeRight1 = null;
	private JLabel lbRight1 = null;
	private JTextField tfConstantValue1 = null;
	private JLabel lbConstantValue1 = null;
	private JScrollPane spComparisons1 = null;
	private JList listComparisons1 = null;
	private JLabel lbComparisons1 = null;
	private JButton bAddComparison1 = null;
	private JButton bDeleteComparison1 = null;
	private JRadioButton rbAttribute = null;
	private JRadioButton rbConstant = null;
	private JRadioButton rbAttribute1 = null;
	private JRadioButton rbConstant1 = null;
	private JLabel lbComparisonLink = null;
	private JRadioButton rbAnd = null;
	private JRadioButton rbOr = null;
	private JLabel lbComparisonLink1 = null;
	private JRadioButton rbAnd1 = null;
	private JRadioButton rbOr1 = null;
	
	private static final char LEFT_SIDE_VAR = 'i',RIGHT_SIDE_VAR = 'j';
	
	
	/**
	 * This is the default constructor
	 */
	public CreateConditionalComparisonPanel() {
		super();
		initialize();
		this.setStepTitle("Definir comparición condicional");
		setBackStep(-1);
	    setNextStep(3);
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
		this.getContentPane().add(getPLatter(), null);
	}

	protected void next(){
		boolean error = false;
		String msg = "Atención:";
			
		//It has to be a singleton, because it's shared through all the panels of the wizard
		//ConstraintWizardProducer constraintProducer = ConstraintWizardProducer.getInstance();
		ConstraintWizardProducer constraintProducer = ((ConstraintWizard)getWizardParent()).getConstraintProducer();
				
		//TODO: Aquí se supone que se crea la constraint con los operandos y operadores que habían definidos.
		
		DefaultListModel listModelFormer = (DefaultListModel)listComparisons.getModel();
		DefaultListModel listModelLatter = (DefaultListModel)listComparisons1.getModel();
		
		if(listModelFormer.isEmpty()){
			error = true;
			msg += "\n-Debe crear al menos una comparación en el antecedente";
		}
		if(listModelLatter.isEmpty()){
			error = true;
			msg += "\n-Debe crear al menos una comparación en el consecuente";
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
		
		// Consecuente
		constraintProducer.setCurrentExpression(ConstraintWizardProducer.CONSEQUENCE_EXPRESSION);
		
		Enumeration<?> latterComparisons = listModelLatter.elements();
		while (latterComparisons.hasMoreElements()) {
			ListConstraintRepresentation elem = (ListConstraintRepresentation) latterComparisons.nextElement();
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
		
		//System.out.println(constraintProducer.getConstraintCode());
		setNextStep(((ConstraintWizard)getWizardParent()).getNextPanel());
		super.next();
	}
	
	protected void makingVisible(){
		System.out.println("Haciendo visible...");
		this.getWizardParent().setPreferredSize(new Dimension(620,830));
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
	
	public void deshabilitarAndOr1(){
		rbAnd1.setEnabled(false);
		rbOr1.setEnabled(false);
		rbAnd1.setSelected(false);
		rbOr1.setSelected(false);
	}
	
	public void habilitarAndOr1(){
		rbAnd1.setEnabled(true);
		rbAnd1.setSelected(true);
		rbOr1.setEnabled(true);
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
			pFormer.setBorder(BorderFactory.createTitledBorder(null, "Antecedente", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
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
			treeLeft.addTreeSelectionListener(new TreeLeftFormerSelectionAction(this));
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
			bAddComparison.addActionListener(new AddComparisonFormerAction(this));
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
			bDeleteComparison.addActionListener(new DeleteComparisonFormerAction(this));
		}
		return bDeleteComparison;
	}

	/**
	 * This method initializes pLatter	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPLatter() {
		if (pLatter == null) {
			lbComparisonLink1 = new JLabel();
			lbComparisonLink1.setBounds(new Rectangle(15, 299, 149, 23));
			lbComparisonLink1.setText("Enlace de la comparasión:");
			lbComparisons1 = new JLabel();
			lbComparisons1.setBounds(new Rectangle(15, 210, 122, 22));
			lbComparisons1.setText("Comparaciones");
			lbConstantValue1 = new JLabel();
			lbConstantValue1.setBounds(new Rectangle(383, 154, 129, 22));
			lbConstantValue1.setText("Valor constante");
			lbRight1 = new JLabel();
			lbRight1.setBounds(new Rectangle(383, 24, 178, 25));
			lbRight1.setText("Variables lado derecho");
			lbOperator1 = new JLabel();
			lbOperator1.setBounds(new Rectangle(208, 40, 148, 28));
			lbOperator1.setText("Operador");
			lbLeft1 = new JLabel();
			lbLeft1.setBounds(new Rectangle(14, 24, 179, 26));
			lbLeft1.setText("Variables lado izquierdo");
			pLatter = new JPanel();
			pLatter.setLayout(null);
			pLatter.setBorder(BorderFactory.createTitledBorder(null, "Consecuente", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pLatter.setLocation(new Point(15, 373));
			pLatter.setSize(new Dimension(576, 330));
			pLatter.add(getSpLeft1(), null);
			pLatter.add(lbLeft1, null);
			pLatter.add(getCbOperators1(), null);
			pLatter.add(getPComparisonType1(), null);
			pLatter.add(lbOperator1, null);
			pLatter.add(getSpRight1(), null);
			pLatter.add(lbRight1, null);
			pLatter.add(getTfConstantValue1(), null);
			pLatter.add(lbConstantValue1, null);
			pLatter.add(getSpComparisons1(), null);
			pLatter.add(lbComparisons1, null);
			pLatter.add(getBAddComparison1(), null);
			pLatter.add(getBDeleteComparison1(), null);
			pLatter.add(lbComparisonLink1, null);
			ButtonGroup linkGroup = new ButtonGroup();
			linkGroup.add(getRbAnd1());
			linkGroup.add(getRbOr1());
			pLatter.add(getRbAnd1(), null);
			pLatter.add(getRbOr1(), null);
		}
		return pLatter;
	}

	/**
	 * This method initializes spLeft1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpLeft1() {
		if (spLeft1 == null) {
			spLeft1 = new JScrollPane();
			spLeft1.setBounds(new Rectangle(14, 48, 181, 156));
			spLeft1.setViewportView(getTreeLeft1());
		}
		return spLeft1;
	}

	/**
	 * This method initializes treeLeft1	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getTreeLeft1() {
		if (treeLeft1 == null) {
			TreeContentManager manager = new TreeContentManager();
			treeLeft1 = new JTree(manager.generateAttributesTree(org.gatt.domain.Session.class));
			treeLeft1.addTreeSelectionListener(new TreeLeftLatterSelectionAction(this));
			
		}
		return treeLeft1;
	}

	/**
	 * This method initializes cbOperators1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbOperators1() {
		if (cbOperators1 == null) {
			cbOperators1 = new JComboBox();
			cbOperators1.setBounds(new Rectangle(208, 69, 155, 26));
			cbOperators1.addItem(new String("[Seleccione una opción...]"));
			cbOperators1.addItem(DefaultComparisonOperator.EQUAL);
			cbOperators1.addItem(DefaultComparisonOperator.LESS_EQUAL_THAN);
			cbOperators1.addItem(DefaultComparisonOperator.MORE_EQUAL_THAN);
			cbOperators1.addItem(DefaultComparisonOperator.LESS_THAN);
			cbOperators1.addItem(DefaultComparisonOperator.MORE_THAN);
			cbOperators1.addItem(DefaultComparisonOperator.NOT_EQUAL);
		}
		return cbOperators1;
	}

	/**
	 * This method initializes pComparisonType1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPComparisonType1() {
		if (pComparisonType1 == null) {
			pComparisonType1 = new JPanel();
			pComparisonType1.setLayout(null);
			pComparisonType1.setBounds(new Rectangle(208, 112, 155, 78));
			pComparisonType1.setBorder(BorderFactory.createTitledBorder(null, "Tipo de comparación", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			ButtonGroup group = new ButtonGroup();
			group.add(getRbAttribute1());
			group.add(getRbConstant1());
			pComparisonType1.add(getRbAttribute1(), null);
			pComparisonType1.add(getRbConstant1(), null);
		}
		return pComparisonType1;
	}

	/**
	 * This method initializes spRight1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpRight1() {
		if (spRight1 == null) {
			spRight1 = new JScrollPane();
			spRight1.setLocation(new Point(382, 48));
			spRight1.setViewportView(getTreeRight1());
			spRight1.setSize(new Dimension(181, 97));
		}
		return spRight1;
	}

	/**
	 * This method initializes treeRight1	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getTreeRight1() {
		if (treeRight1 == null) {
			TreeContentManager manager = new TreeContentManager();
			treeRight1 = new JTree(manager.generateAttributesTree(org.gatt.domain.Session.class));
			treeRight1.setSize(new Dimension(181, 153));
		}
		return treeRight1;
	}

	/**
	 * This method initializes tfConstantValue1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfConstantValue1() {
		if (tfConstantValue1 == null) {
			tfConstantValue1 = new JTextField();
			tfConstantValue1.setBounds(new Rectangle(382, 176, 180, 26));
			tfConstantValue1.setEnabled(false);
		}
		return tfConstantValue1;
	}

	/**
	 * This method initializes spComparisons1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpComparisons1() {
		if (spComparisons1 == null) {
			spComparisons1 = new JScrollPane();
			spComparisons1.setBounds(new Rectangle(15, 230, 548, 60));
			spComparisons1.setViewportView(getListComparisons1());
		}
		return spComparisons1;
	}

	/**
	 * This method initializes listComparisons1	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getListComparisons1() {
		if (listComparisons1 == null) {
			DefaultListModel listModel = new DefaultListModel();
			listComparisons1 = new JList(listModel);
			listComparisons1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		}
		return listComparisons1;
	}

	/**
	 * This method initializes bAddComparison1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBAddComparison1() {
		if (bAddComparison1 == null) {
			bAddComparison1 = new JButton();
			bAddComparison1.setBounds(new Rectangle(389, 297, 81, 22));
			bAddComparison1.setText("Agregar");
			bAddComparison1.addActionListener(new AddComparisonLatterAction(this));
		}
		return bAddComparison1;
	}

	/**
	 * This method initializes bDeleteComparison1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBDeleteComparison1() {
		if (bDeleteComparison1 == null) {
			bDeleteComparison1 = new JButton();
			bDeleteComparison1.setLocation(new Point(481, 297));
			bDeleteComparison1.setText("Eliminar");
			bDeleteComparison1.setSize(new Dimension(81, 22));
			bDeleteComparison1.addActionListener(new DeleteComparisonLatterAction(this));
		}
		return bDeleteComparison1;
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
			rbAttribute.addActionListener(new CompTypeAttributeFormerSelectedAction(this));
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
			rbConstant.addActionListener(new CompTypeConstantFormerSelectedAction(this));
		}
		return rbConstant;
	}

	/**
	 * This method initializes rbAttribute1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbAttribute1() {
		if (rbAttribute1 == null) {
			rbAttribute1 = new JRadioButton();
			rbAttribute1.setText("Atributo");
			rbAttribute1.setLocation(new Point(14, 20));
			rbAttribute1.setSize(new Dimension(70, 24));
			rbAttribute1.setSelected(true);
			rbAttribute1.addActionListener(new CompTypeAttributeLatterSelectedAction(this));
		}
		return rbAttribute1;
	}

	/**
	 * This method initializes rbConstant1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbConstant1() {
		if (rbConstant1 == null) {
			rbConstant1 = new JRadioButton();
			rbConstant1.setText("Valor constante");
			rbConstant1.setLocation(new Point(14, 47));
			rbConstant1.setSize(new Dimension(115, 24));
			rbConstant1.addActionListener(new CompTypeConstantLatterSelectedAction(this));
		}
		return rbConstant1;
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

	/**
	 * This method initializes rbAnd1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbAnd1() {
		if (rbAnd1 == null) {
			rbAnd1 = new JRadioButton();
			rbAnd1.setBounds(new Rectangle(170, 298, 67, 24));
			rbAnd1.setText("Y (AND)");
			rbAnd1.setEnabled(false);
		}
		return rbAnd1;
	}

	/**
	 * This method initializes rbOr1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRbOr1() {
		if (rbOr1 == null) {
			rbOr1 = new JRadioButton();
			rbOr1.setBounds(new Rectangle(245, 299, 62, 24));
			rbOr1.setText("O (OR)");
			rbOr1.setEnabled(false);
		}
		return rbOr1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
