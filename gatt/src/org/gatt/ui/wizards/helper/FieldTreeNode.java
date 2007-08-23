package org.gatt.ui.wizards.helper;

import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;

import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;

public class FieldTreeNode extends DefaultMutableTreeNode {
	
	private static JFigIF config;
	static{
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		config = JFig.getInstance(locator);
	}
	public FieldTreeNode(Object f){
		super(f);
	}
	public FieldTreeNode(Object f, boolean b){
		super(f, b);
	}
	public String getJavaName(){
		if( super.getUserObject() instanceof String)
			return (String)super.getUserObject();		
		return ((Field)super.getUserObject()).getName();		
	}
	public String toString(){	
		
		String key = "", value = "";		
		if( super.getUserObject() instanceof String){
			key = (String)super.getUserObject(); 
		}else{
			key = ((Field)super.getUserObject()).getName();
		}
		
		try{
			value = config.getValue("TreeKeys", key);
		}catch(JFigException jFigEx){
			jFigEx.printStackTrace();
		}
		return value;
		
	}
}
