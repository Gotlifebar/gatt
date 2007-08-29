package org.gatt.ui.wizards.commands;

import java.lang.reflect.Field;

import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;

import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;
import org.gatt.ui.wizards.CreateConditionalComparisonPanel;
import org.gatt.ui.wizards.helper.FieldTreeNode;
import org.gatt.ui.wizards.helper.TreeContentManager;

public class TreeLeftFormerSelectionAction implements TreeSelectionListener {

	private CreateConditionalComparisonPanel panel;
	
	public TreeLeftFormerSelectionAction(CreateConditionalComparisonPanel panel){
		this.panel = panel;
	}
	
	public void valueChanged(TreeSelectionEvent e) {
		
		JTree tree = (JTree)e.getSource();
		FieldTreeNode treeNode = (FieldTreeNode)tree.getSelectionPath().getLastPathComponent();
		if(!treeNode.isLeaf()){
			return;
		}
		Field field = (Field)treeNode.getUserObject();
		
		TreeContentManager manager = new TreeContentManager();
		
		panel.getTreeRight().setModel(new DefaultTreeModel(manager.generateAttributesTree(org.gatt.domain.Session.class, field.getType())));
		JComboBox cbOp = panel.getCbOperators(); 
		
		
		cbOp.removeAllItems();
		cbOp.addItem(new String("[Seleccione una opción...]"));
		if(field.getType() == String.class){			
			cbOp.addItem(StringComparisonOperator.EQUALS);
			cbOp.addItem(StringComparisonOperator.NOT_EQUAL);
		}else{
			cbOp.addItem(DefaultComparisonOperator.EQUAL);
			cbOp.addItem(DefaultComparisonOperator.LESS_EQUAL_THAN);
			cbOp.addItem(DefaultComparisonOperator.MORE_EQUAL_THAN);
			cbOp.addItem(DefaultComparisonOperator.LESS_THAN);
			cbOp.addItem(DefaultComparisonOperator.MORE_THAN);
			cbOp.addItem(DefaultComparisonOperator.NOT_EQUAL);
		}
		
	}

}
