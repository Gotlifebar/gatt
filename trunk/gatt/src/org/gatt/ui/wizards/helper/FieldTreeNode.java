package org.gatt.ui.wizards.helper;

import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;

public class FieldTreeNode extends DefaultMutableTreeNode {
	public FieldTreeNode(Object f){
		super(f);
	}
	public FieldTreeNode(Object f, boolean b){
		super(f, b);
	}	
	public String toString(){
		if( super.getUserObject() instanceof String)
			return (String)super.getUserObject();
		return ((Field)super.getUserObject()).getName();
	}
}
