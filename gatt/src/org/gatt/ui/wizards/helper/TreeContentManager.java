package org.gatt.ui.wizards.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author david
 * manages the content of the tree controls in one panel of the constraint wizard
 */
public class TreeContentManager {
	/**
	 * @param c
	 * generates the tree structure for a given class
	 */
	private boolean generateFor(Class c){
		if(c == null)
			return false;
		if(c.isPrimitive())
			return false;
		if(c.getName().contains("java.lang"))
			return false;
		return true;
	}
	
	/**
	 * @param root
	 * @param f
	 * @param filter
	 */
	private void addNodeTo(FieldTreeNode root, Field f, Class filter){		
		if( Modifier.isStatic(f.getModifiers()) )
			return;		
		if( filter != null && f.getType() != filter)
			return;		 
		addNodeTo(root, new FieldTreeNode(f));
	}
	
	/**
	 * @param root
	 * @param node
	 */
	private void addNodeTo(FieldTreeNode root, FieldTreeNode node){
		if( node.toString() != null )
			root.add(node);
	}
	
	/**
	 * @param fields
	 * @return
	 */
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
	
	/**
	 * @param c
	 * @return
	 */
	public FieldTreeNode generateAttributesTree(Class c){
		return this.generateAttributesTree(c, null);
	}
	
	/**
	 * @param c
	 * @param filter
	 * @return
	 */
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
	/**
	 * @param field
	 * @param filter
	 * @return
	 */
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
	
	/**
	 * @param fieldName
	 * @return
	 */
	private String createGetter(String fieldName){
		StringBuffer buf = new StringBuffer("get");
		buf.append(fieldName);
		buf.setCharAt(3,Character.toUpperCase(fieldName.charAt(0)));
		buf.append("()");
		return buf.toString();
	}
	
	/**
	 * @param node
	 * @param var
	 * @return
	 */
	public String getFieldJavaStringFromNode(FieldTreeNode node, char var){
		String name = createGetter(node.getJavaName());
		FieldTreeNode n = (FieldTreeNode)node.getParent();
		while( n.getParent() != null ){
			name = createGetter(n.getJavaName()) + "." + name;
			n = (FieldTreeNode)n.getParent();
		}
		name = "session[" + String.valueOf(var) + "]." + name;
		return name;
	}
	
	/**
	 * @param node
	 * @return
	 */
	public String getFieldReadableStringFromNode(FieldTreeNode node){
		String readable = node.toString();
		FieldTreeNode n = (FieldTreeNode)node.getParent();
		while( n.getParent() != null ){
			readable = n.toString() + " -> " + readable;
			n = (FieldTreeNode)n.getParent();		
		}
		return readable;
	}
	
	/*public static void main(String[] ar){
		
	}*/
	
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
