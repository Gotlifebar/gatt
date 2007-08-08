package org.gatt.ui.wizards.helper;

import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class TreeFiller {
	private boolean generateFor(Class c){
		if(c == null)
			return false;
		if(c.isPrimitive())
			return false;
		if(c.getName().contains("java.lang"))
			return false;
		return true;
	}
	
	private Field[] getAndArrange(Field[] fields){
		Field[] fs = new Field[fields.length];
		int latter = fs.length - 1;
		int former = 0;
		for(Field f : fields){
			if(f.getType().isPrimitive()){
				fs[former] = f;
				former++;
				continue;
			}
			fs[latter] = f;
			latter--;
		}
		return fs;
	}
	
	public DefaultMutableTreeNode generateAttributesTree(Class c){		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(c.getSimpleName(), true);
		//System.out.println("Generating for " + c.getSimpleName());
		//Extract fields and arrange them.
		Field[] fields = getAndArrange(c.getDeclaredFields());
		for(Field f : fields){
		//	System.out.println("Trying with: " + f.getName());
			//if the field is not a primitive type
			if(generateFor(f.getType()))
				root.add(generateAttributesTree(f));
			else
				root.add(new DefaultMutableTreeNode(f));		
		}
		return root;
	}
	private DefaultMutableTreeNode generateAttributesTree(Field field){		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(field, true);	
		//System.out.println("Generating for " + field.getName());
		//Extract fields and arrange them.
		Field[] fields = getAndArrange(field.getType().getDeclaredFields());
		for(Field f : fields)
			//if the field is not a primitive type
			if(generateFor(f.getType()))
				root.add(generateAttributesTree(f));
			else
				root.add(new DefaultMutableTreeNode(f));
		return root;
	}
	
	
	/*public static int depth = -1;
	
	public static void printPad(){
		for(int i = 0; i < depth; i++)
			System.out.print("\t");
	}*/
	
	/*public static void printNodeContent(DefaultMutableTreeNode node){
		printPad();
		if(node.getUserObject() instanceof Field){
			Field f = (Field)node.getUserObject();
			System.out.println(f.getName());
		}else{
			System.out.println(node.toString());
		}
			
	}
	
	public static void printTreeNode(DefaultMutableTreeNode root){
		depth++;
		printNodeContent(root);
		if(!root.isLeaf())		
			for(int i = 0; i < root.getChildCount(); i++)
				printTreeNode((DefaultMutableTreeNode)root.getChildAt(i));		
		depth--;
	}
	
	
	public static void main(String[] args) {
		//printTreeNode(generateAttributesTree(Composite.class));
	}*/
}
