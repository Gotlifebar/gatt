package org.gatt.ui.wizards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;

import org.freixas.jwizard.JWizardPanel;
import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.ui.wizards.commands.CompTypeAttributeSelectedAction;
import org.gatt.ui.wizards.commands.CompTypeConstantSelectedAction;
import org.gatt.ui.wizards.commands.TreeLeftSelectionAction;
import org.gatt.ui.wizards.helper.TreeContentManager;

public class CreateComparisonPanel extends JWizardPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollTreeLeft = null;
	private JTree treeLeft = null;
	private JScrollPane scrollTreeRight = null;
	private JTree treeRight = null;
	private JComboBox comboOperator = null;
	private JRadioButton radioConstant = null;
	private JRadioButton radioAttribute = null;
	private JTextField textFreeDomainValue = null;
	private JPanel pVarLeft = null;
	private JPanel pVarsRight = null;
	private JPanel pOperator = null;
	private JPanel pCompType = null;
	private JPanel pConstantVal = null;
	/**
	 * This is the default constructor
	 */
	public CreateComparisonPanel() {
		super();
		this.setStepTitle("Definir comparación");
		initialize();
		setBackStep(0);
	    setNextStep(2);
	}
	
	protected void next(){
		boolean error = false;
		String msg = "Atención:";
		
		if(this.getTreeLeft().getSelectionCount() == 0){
			error = true;
			msg += "\n" + "- Debe seleccionar una variable en la lista del lado izquierdo.";
		}
		
		if(this.getComboOperator().getSelectedIndex() == 0){
			error = true;
			msg += "\n" + "- Debe seleccionar un operador.";
		}
		
		if(this.getRadioConstant().isSelected() && this.getTextFreeDomainValue().getText().equals("")){
			error = true;
			msg += "\n" + "- Debe ingresar un valor constante.";
		}
		
		if(this.getRadioAttribute().isSelected() && this.getTreeRight().getSelectionCount() == 0){
			error = true;
			msg += "\n" + "- Debe seleccionar un atributo en la lista del lado derecho.";
		}
		
		if(error){
			JOptionPane.showMessageDialog(
					this,
					msg,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(getRadioConstant().isSelected()){ //String comparison
			
		}else{
			
		}
		
		super.next();
		return;
	}
	
	protected void makingVisible(){
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		
		// La fachada
		if(wizard.getConstraintType() == ConstraintWizard.ConstraintType.SIMPLE){
			this.setNextStep(3);
		}else{
			this.setNextStep(2);
		}
		
		if(!wizard.isSameRound()){
			this.setBackStep(-1);
			resetView();
		}
		super.makingVisible();
	}
	
	private void resetView(){
		
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(602, 356);
		this.getContentPane().setSize(602, 356);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(getPVarLeft(), null);
		this.getContentPane().add(getPVarsRight(), null);
		this.getContentPane().add(getPOperator(), null);
		this.getContentPane().add(getPCompType(), null);
		this.getContentPane().add(getPConstantVal(), null);
	}

	/**
	 * This method initializes scrollTreeLeft	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getScrollTreeLeft() {
		if (scrollTreeLeft == null) {
			scrollTreeLeft = new JScrollPane();
			scrollTreeLeft.setViewportView(getTreeLeft());
		}
		return scrollTreeLeft;
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
			treeLeft.addTreeSelectionListener(new TreeLeftSelectionAction(this));
		}
		return treeLeft;
	}

	/**
	 * This method initializes scrollTreeRight	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getScrollTreeRight() {
		if (scrollTreeRight == null) {
			scrollTreeRight = new JScrollPane();
			scrollTreeRight.setViewportView(getTreeRight());
		}
		return scrollTreeRight;
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
		}
		return treeRight;
	}

	/**
	 * This method initializes comboOperator	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getComboOperator() {
		if (comboOperator == null) {
			comboOperator = new JComboBox();
			comboOperator.setPreferredSize(new Dimension(120, 25));
			comboOperator.addItem(new String("[Seleccione una opción...]"));
			comboOperator.addItem(DefaultComparisonOperator.EQUAL);
			comboOperator.addItem(DefaultComparisonOperator.LESS_EQUAL_THAN);
			comboOperator.addItem(DefaultComparisonOperator.MORE_EQUAL_THAN);
			comboOperator.addItem(DefaultComparisonOperator.LESS_THAN);
			comboOperator.addItem(DefaultComparisonOperator.MORE_THAN);
			comboOperator.addItem(DefaultComparisonOperator.NOT_EQUAL);
		}
		return comboOperator;
	}

	/**
	 * This method initializes radioConstant	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRadioConstant() {
		if (radioConstant == null) {
			radioConstant = new JRadioButton();
			radioConstant.setText("Valor constante");
			radioConstant.addActionListener(new CompTypeConstantSelectedAction(this));
		}
		return radioConstant;
	}

	/**
	 * This method initializes radioAttribute	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getRadioAttribute() {
		if (radioAttribute == null) {
			radioAttribute = new JRadioButton();
			radioAttribute.setText("Atributo");
			radioAttribute.setSelected(true);
			radioAttribute.addActionListener(new CompTypeAttributeSelectedAction(this));
		}
		return radioAttribute;
	}

	/**
	 * This method initializes textFreeDomainValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextFreeDomainValue() {
		if (textFreeDomainValue == null) {
			textFreeDomainValue = new JTextField();
			textFreeDomainValue.setPreferredSize(new Dimension(150, 30));
			textFreeDomainValue.setEnabled(false);
		}
		return textFreeDomainValue;
	}

	/**
	 * This method initializes pVarLeft	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPVarLeft() {
		if (pVarLeft == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			pVarLeft = new JPanel();
			pVarLeft.setLayout(new GridBagLayout());
			pVarLeft.setBounds(new Rectangle(15, 15, 201, 321));
			pVarLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
			pVarLeft.setBorder(BorderFactory.createTitledBorder(null, "Variable lado izquierdo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pVarLeft.add(getScrollTreeLeft(), gridBagConstraints);
		}
		return pVarLeft;
	}

	/**
	 * This method initializes pVarsRight	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPVarsRight() {
		if (pVarsRight == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.weightx = 1.0;
			pVarsRight = new JPanel();
			pVarsRight.setLayout(new GridBagLayout());
			pVarsRight.setLocation(new Point(382, 15));
			pVarsRight.setSize(new Dimension(201, 222));
			pVarsRight.setBorder(BorderFactory.createTitledBorder(null, "Atributos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pVarsRight.add(getScrollTreeRight(), gridBagConstraints1);
		}
		return pVarsRight;
	}

	/**
	 * This method initializes pOperator	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPOperator() {
		if (pOperator == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.weightx = 1.0;
			pOperator = new JPanel();
			pOperator.setLayout(new GridBagLayout());
			pOperator.setBorder(BorderFactory.createTitledBorder(null, "Operador", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pOperator.setSize(new Dimension(144, 52));
			pOperator.setLocation(new Point(226, 90));
			pOperator.add(getComboOperator(), gridBagConstraints2);
		}
		return pOperator;
	}

	/**
	 * This method initializes pCompType	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPCompType() {
		if (pCompType == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			gridLayout1.setVgap(10);
			gridLayout1.setColumns(1);
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setVgap(10);
			gridLayout.setColumns(1);
			pCompType = new JPanel();
			pCompType.setLayout(gridLayout1);
			pCompType.setBounds(new Rectangle(226, 179, 145, 70));
			pCompType.setBorder(BorderFactory.createTitledBorder(null, "Tipo de comparación", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			
			ButtonGroup group = new ButtonGroup();
			group.add(getRadioAttribute());
			group.add(getRadioConstant());
			
			pCompType.add(getRadioAttribute(), null);
			pCompType.add(getRadioConstant(), null);
		}
		return pCompType;
	}

	/**
	 * This method initializes pConstantVal	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPConstantVal() {
		if (pConstantVal == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.weightx = 1.0;
			pConstantVal = new JPanel();
			pConstantVal.setLayout(new GridBagLayout());
			pConstantVal.setBorder(BorderFactory.createTitledBorder(null, "Valor constante", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pConstantVal.setSize(new Dimension(201, 88));
			pConstantVal.setLocation(new Point(382, 248));
			pConstantVal.add(getTextFreeDomainValue(), gridBagConstraints3);
		}
		return pConstantVal;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
