package org.gatt.ui.wizards.commands;

import java.lang.reflect.Field;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;

import org.gatt.constraint.codifiable.boolexpression.DefaultComparisonOperator;
import org.gatt.constraint.codifiable.stringexpression.StringComparisonOperator;
import org.gatt.ui.wizards.CreateComparisonPanel;
import org.gatt.ui.wizards.helper.FieldTreeNode;
import org.gatt.ui.wizards.helper.TreeContentManager;

public class TreeLeftSelectionAction implements TreeSelectionListener {

	
	private CreateComparisonPanel panel;
	
	public TreeLeftSelectionAction(CreateComparisonPanel panel){
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
		
		panel.getComboOperator().removeAllItems();
		panel.getComboOperator().addItem(new String("[Seleccione una opción...]"));
		if(field.getType() == String.class){			
			panel.getComboOperator().addItem(StringComparisonOperator.EQUALS);
			panel.getComboOperator().addItem(StringComparisonOperator.NOT_EQUAL);
		}else{
			panel.getComboOperator().addItem(DefaultComparisonOperator.EQUAL);
			panel.getComboOperator().addItem(DefaultComparisonOperator.LESS_EQUAL_THAN);
			panel.getComboOperator().addItem(DefaultComparisonOperator.MORE_EQUAL_THAN);
			panel.getComboOperator().addItem(DefaultComparisonOperator.LESS_THAN);
			panel.getComboOperator().addItem(DefaultComparisonOperator.MORE_THAN);
			panel.getComboOperator().addItem(DefaultComparisonOperator.NOT_EQUAL);
		}
		
	}

}
