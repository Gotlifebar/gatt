package org.gatt.ui.wizards.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.swing.tree.TreeNode;

public class TreeContentManager {
	private boolean generateFor(Class c){
		if(c == null)
			return false;
		if(c.isPrimitive())
			return false;
		if(c.getName().contains("java.lang"))
			return false;
		return true;
	}
	
	private void addNodeTo(FieldTreeNode root, Field f, Class filter){		
		if( Modifier.isStatic(f.getModifiers()) )
			return;		
		if( filter != null && f.getType() != filter)
			return;
		root.add(new FieldTreeNode(f));
	}
	
	private void addNodeTo(FieldTreeNode root, FieldTreeNode node){
		root.add(node);
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
	
	public FieldTreeNode generateAttributesTree(Class c){
		return this.generateAttributesTree(c, null);
	}
	
	public FieldTreeNode generateAttributesTree(Class c, Class filter){
		FieldTreeNode root = new FieldTreeNode(c.getSimpleName(), true);		
		//System.out.println("Generating for " + c.getSimpleName());
		//Extract fields and arrange them.
		Field[] fields = getAndArrange(c.getDeclaredFields());
		for(Field f : fields){
		//	System.out.println("Trying with: " + f.getName());
			//if the field is not a primitive type
			if(generateFor(f.getType()))
				addNodeTo(root, generateAttributesTree(f, filter));
				//root.add(generateAttributesTree(f));
			else
				addNodeTo(root, f, filter);
				//root.add(new FieldTreeNode(f));
			
		}
		return root;
	}
	private FieldTreeNode generateAttributesTree(Field field, Class filter){		
		FieldTreeNode root = new FieldTreeNode(field, true);	
		//System.out.println("Generating for " + field.getName());
		//Extract fields and arrange them.
		Field[] fields = getAndArrange(field.getType().getDeclaredFields());
		for(Field f : fields)
			//if the field is not a primitive type
			if(generateFor(f.getType()))
				addNodeTo(root, generateAttributesTree(f, filter));
				//root.add(generateAttributesTree(f));
			else
				addNodeTo(root, f, filter);
				//root.add(new FieldTreeNode(f));
		
		return root;
	}
	
	public String getFieldNameFromNode(TreeNode node){
		String name = node.toString();
		TreeNode n = node.getParent();
		while( n != null ){
			name = n.toString() + "." + name;
			n = n.getParent();
		}
		return name;
	}
	
	public static void main(String[] ar){
		
	}
	
	/*public static int depth = -1;
	
	public static void printPad(){
		for(int i = 0; i < depth; i++)
			System.out.print("\t");
	}*/
	
	/*public static void printNodeContent(FieldTreeNode node){
		printPad();
		if(node.getUserObject() instanceof Field){
			Field f = (Field)node.getUserObject();
			System.out.println(f.getName());
		}else{
			System.out.println(node.toString());
		}
			
	}
	
	public static void printTreeNode(FieldTreeNode root){
		depth++;
		printNodeContent(root);
		if(!root.isLeaf())		
			for(int i = 0; i < root.getChildCount(); i++)
				printTreeNode((FieldTreeNode)root.getChildAt(i));		
		depth--;
	}
	
	
	public static void main(String[] args) {
		//printTreeNode(generateAttributesTree(Composite.class));
	}*/
}
