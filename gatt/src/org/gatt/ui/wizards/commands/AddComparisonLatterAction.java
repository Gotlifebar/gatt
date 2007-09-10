package org.gatt.ui.wizards.commands;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.gatt.constraint.codifiable.ConstraintCodifiableFacade;
import org.gatt.constraint.codifiable.Operator;
import org.gatt.constraint.codifiable.boolexpression.ComparableOperand;
import org.gatt.ui.wizards.ConstraintWizard;
import org.gatt.ui.wizards.CreateConditionalComparisonPanel;
import org.gatt.ui.wizards.ConstraintWizard.ComplementType;
import org.gatt.ui.wizards.helper.ConstraintWizardProducer;
import org.gatt.ui.wizards.helper.FieldTreeNode;
import org.gatt.ui.wizards.helper.ListConstraintRepresentation;
import org.gatt.ui.wizards.helper.TreeContentManager;

public class AddComparisonLatterAction extends AbstractAction {
	
	private CreateConditionalComparisonPanel panel;
	
	private static final char LEFT_SIDE_VAR = 'i',RIGHT_SIDE_VAR = 'j';
	
	public AddComparisonLatterAction(CreateConditionalComparisonPanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		boolean error = false;
		String msg = "Atención:";
	
		if(panel.getTreeLeft1().getSelectionCount() == 0){
			error = true;
			msg += "\n" + "- Debe seleccionar una variable en la lista del lado izquierdo del consecuente.";
		}
		
		if(panel.getCbOperators1().getSelectedIndex() == 0){
			error = true;
			msg += "\n" + "- Debe seleccionar un operador en el consecuente.";
		}
		
		if(panel.getRbConstant1().isSelected() && panel.getTfConstantValue1().getText().equals("")){
			error = true;
			msg += "\n" + "- Debe ingresar un valor constante en el consecuente.";
		}
		
		if(panel.getRbAttribute1().isSelected() && panel.getTreeRight1().getSelectionCount() == 0){
			error = true;
			msg += "\n" + "- Debe seleccionar un atributo en la lista del lado derecho del consecuente.";
		}
		
		if(error){
			JOptionPane.showMessageDialog(
					panel,
					msg,
					"Error creando comparación en el consecuente",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		ConstraintWizardProducer producer = ((ConstraintWizard)panel.getWizardParent()).getConstraintProducer();
		producer.addUsedVar(LEFT_SIDE_VAR);
		ConstraintCodifiableFacade facade = new ConstraintCodifiableFacade();
		TreeContentManager treeManager = new TreeContentManager();
		
		FieldTreeNode treeNodeLeft = (FieldTreeNode)panel.getTreeLeft1().getSelectionPath().getLastPathComponent();
		Field leftField = (Field)treeNodeLeft.getUserObject();
		
		ComparableOperand operand1 = facade.createOperand(
				treeManager.getFieldJavaStringFromNode(treeNodeLeft, LEFT_SIDE_VAR),				
				treeNodeLeft.toString(),//TODO: Construct the real string
				treeManager.getFieldReadableStringFromNode(treeNodeLeft), //FIXME: Lol !!!!!
				leftField.getType()); 
		
		Operator operator = (Operator)panel.getCbOperators1().getSelectedItem();
		
		ComparableOperand operand2 = null;
		if( panel.getRbAttribute1().isSelected() ){	
			FieldTreeNode treeNodeRight = (FieldTreeNode)panel.getTreeRight1().getSelectionPath().getLastPathComponent();
			char var = LEFT_SIDE_VAR;
			if( treeNodeRight.getUserObject().equals(treeNodeLeft.getUserObject()))
				var = RIGHT_SIDE_VAR;			
			producer.addUsedVar(var);
			
			operand2 = facade.createOperand(
						treeManager.getFieldJavaStringFromNode(treeNodeRight, var),
						treeNodeRight.toString(),
						treeManager.getFieldReadableStringFromNode(treeNodeRight),
						leftField.getType());
		}else{
			String javaString = "";
			if(leftField.getType() == String.class )
				javaString ="\"" + panel.getTfConstantValue1().getText() + "\"";
			else
				javaString = panel.getTfConstantValue1().getText() ;
			//TODO: Make javaString validation....
			operand2 = facade.createOperand(
					javaString,
					panel.getTfConstantValue1().getText(),
					panel.getTfConstantValue1().getText(),
					leftField.getType());
		}
		
		ComplementType cType = null;
		if(panel.getRbAnd1().isSelected()){
			cType = ComplementType.AND;
		}else if(panel.getRbOr1().isSelected()){
			cType = ComplementType.OR;
		}
		
		ListConstraintRepresentation item = new ListConstraintRepresentation(operator,
				operand1,
				operand2,
				leftField.getType(),
				cType);
		
		DefaultListModel listModel = (DefaultListModel)panel.getListComparisons1().getModel();
		listModel.addElement(item);
		
		panel.habilitarAndOr1();
	}

}
