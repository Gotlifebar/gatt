package org.gatt.ui.wizards.helper;

import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;

import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;

/**
 * @author david
 * represent a node of a tree used in the wizard
 */
public class FieldTreeNode extends DefaultMutableTreeNode {
	
	/**
	 * configuration file manager
	 */
	private static JFigIF config;
	
	static{
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		config = JFig.getInstance(locator);
	}
	/**
	 * constructor
	 * @param f
	 */
	public FieldTreeNode(Object f){
		super(f);
	}
	/**
	 * constructor
	 * @param f
	 * @param b
	 */
	public FieldTreeNode(Object f, boolean b){
		super(f, b);
	}
	/**
	 * return the java name of the node
	 */
	public String getJavaName(){
		if( super.getUserObject() instanceof String)
			return (String)super.getUserObject();		
		return ((Field)super.getUserObject()).getName();		
	}
	/** 
	 * return the corresponding name of the node in the TreeKeys section in the configuratio file
	 * @see javax.swing.tree.DefaultMutableTreeNode#toString()
	 */
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
			//jFigEx.printStackTrace();
			return null;
		}
		return value;
		
	}
}
