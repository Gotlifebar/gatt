package org.gatt.constraint.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.ui.wizards.helper.ConstraintWizardInfoWrapper;
import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XMLConstraintWriter {
	
	protected final String CONSTRAINT_ELEMENT = "constraint";
	protected final String DESCRIPTION_ELEMENT = "description";
	protected final String IMPLEMENTATION_ELEMENT = "implementation";
	protected final String DEFINITION_ELEMENT = "definition";
	protected final String ID_ATTR = "id";
	protected final String NAME_ATTR = "name";
	protected final String SIGNIFICANCE_ATTR = "significance";
	protected final String INDENT = "     ";
	
	public static XMLConstraintWriter instance;
	private XMLOutputter outp;
	private File file;
	private String dir;
	
	
	private XMLConstraintWriter(){
		
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);

		outp = new XMLOutputter();
		outp.setFormat(Format.getPrettyFormat());
		
		String sFile = null;
		try{
			sFile = config.getValue("XMLWriterInfo", "FilePath");
			dir = config.getValue("XMLWriterInfo", "DirPath");	
		}catch(JFigException jFigEx){
			jFigEx.printStackTrace();
		}
		
		file = new File(sFile);
	}
	
	public static XMLConstraintWriter getInstance(){
		if(instance == null)
			instance = new XMLConstraintWriter();
		
		return instance;
	}
	
	public void write(ConstraintInfo cInfo){
		SAXBuilder builder = new SAXBuilder(); 
		Document doc = null;
		try{
			doc = builder.build(file);
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		catch(JDOMException domEx){
			domEx.printStackTrace();
		}
		
		Element constraint = new Element(CONSTRAINT_ELEMENT);
		constraint.setAttribute(ID_ATTR,cInfo.getId());
		constraint.setAttribute(NAME_ATTR,cInfo.getName());
		constraint.setAttribute(SIGNIFICANCE_ATTR,Double.toString(cInfo.getSignificance()));
		
		Element description = new Element(DESCRIPTION_ELEMENT);
		description.addContent(new CDATA(cInfo.getDescription()));
		
		Element definition = new Element(DEFINITION_ELEMENT);
		definition.addContent(new CDATA(cInfo.getDefinition()));
		
		Element impl = new Element(IMPLEMENTATION_ELEMENT);
		impl.addContent(new CDATA(cInfo.getStrategyCodeImplementation()));
		
		constraint.addContent(description);
		constraint.addContent(definition);
		constraint.addContent(impl);
		
		doc.getRootElement().addContent(constraint);
		
		FileOutputStream fos;
		try{
			fos = new FileOutputStream(file);
			outp.output(doc, fos);
			fos.close();
		}catch(FileNotFoundException fEx){
			fEx.printStackTrace();
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	
	public void write(List<ConstraintInfo> constraints){
		SAXBuilder builder = new SAXBuilder(); 
		Document doc = null;
		try{
			doc = builder.build(file);
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		catch(JDOMException domEx){
			domEx.printStackTrace();
		}
		
		ArrayList<Element> cs = new ArrayList<Element>();
		
		for (Iterator<ConstraintInfo> iterator = constraints.iterator(); iterator.hasNext();) {
			ConstraintInfo cInfo = (ConstraintInfo) iterator.next();
							
			Element constraint = new Element(CONSTRAINT_ELEMENT);
			constraint.setAttribute(ID_ATTR,cInfo.getId());
			constraint.setAttribute(NAME_ATTR,cInfo.getName());
			constraint.setAttribute(SIGNIFICANCE_ATTR,Double.toString(cInfo.getSignificance()));
	
			Element description = new Element(DESCRIPTION_ELEMENT);
			description.addContent(new CDATA(cInfo.getDescription()));
			
			Element definition = new Element(DEFINITION_ELEMENT);
			definition.addContent(new CDATA(cInfo.getDefinition()));
			
			Element impl = new Element(IMPLEMENTATION_ELEMENT);
			impl.addContent(new CDATA(cInfo.getStrategyCodeImplementation()));
			
			constraint.addContent(description);
			constraint.addContent(definition);
			constraint.addContent(impl);
			
			cs.add(constraint);
		}
		
		doc.getRootElement().addContent(cs);
		
		FileOutputStream fos;
		try{
			fos = new FileOutputStream(file);
			outp.output(doc, fos);
			fos.close();
		}catch(FileNotFoundException fEx){
			fEx.printStackTrace();
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	
	public void deleteConstraint(String id){
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try{
			doc = builder.build(file);
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		catch(JDOMException domEx){
			domEx.printStackTrace();
		}
		
		Element root = doc.getRootElement();
		
		List children = root.getChildren();
		int index = 0;
		Iterator i = children.iterator();
		Element c = null;
		while(i.hasNext()){
			c = (Element)i.next();
			if(c.getAttributeValue("id").equals(id))
				break;
			index++;
		}
		root.removeContent(c);
		
		FileOutputStream fos;
		try{
			fos = new FileOutputStream(file);
			outp.output(doc, fos);
			fos.close();
		}catch(FileNotFoundException fEx){
			fEx.printStackTrace();
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		
		deleteConstraintWrapperFile(id);
	}
	
	public void updateConstraint(ConstraintInfo cInfo){
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try{
			doc = builder.build(file);
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		catch(JDOMException domEx){
			domEx.printStackTrace();
		}
		
		Element root = doc.getRootElement();
		
		List children = root.getChildren();
		int index = 0;
		Iterator i = children.iterator();
		Element c = null;
		while(i.hasNext()){
			c = (Element)i.next();
			if(c.getAttributeValue("id").equals(cInfo.getId()))
				break;
			index++;
		}
		
		c.removeContent();
		
		c.setAttribute(ID_ATTR,cInfo.getId());
		c.setAttribute(NAME_ATTR,cInfo.getName());
		c.setAttribute(SIGNIFICANCE_ATTR,Double.toString(cInfo.getSignificance()));
		
		Element description = new Element(DESCRIPTION_ELEMENT);
		description.addContent(new CDATA(cInfo.getDescription()));
		
		Element definition = new Element(DEFINITION_ELEMENT);
		definition.addContent(new CDATA(cInfo.getDefinition()));
		
		Element impl = new Element(IMPLEMENTATION_ELEMENT);
		impl.addContent(new CDATA(cInfo.getStrategyCodeImplementation()));
		c.addContent(description);
		c.addContent(definition);
		c.addContent(impl);
		
		children.set(index, c);
		
		FileOutputStream fos;
		try{
			fos = new FileOutputStream(file);
			outp.output(doc, fos);
			fos.close();
		}catch(FileNotFoundException fEx){
			fEx.printStackTrace();
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	
	public void writeConstraintWrapper(ConstraintWizardInfoWrapper constraintWrapper){
		String nFile = "Constraint_" + constraintWrapper.getConstraintInfo().getId() + ".constraint";
		File cFile = new File(dir + nFile);
		
		try{
			FileOutputStream fos = new FileOutputStream(cFile);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(constraintWrapper);
			os.close();
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	
	private void deleteConstraintWrapperFile(String id){
		String nFile = "Constraint_" + id + ".constraint";
		File cFile = new File(dir + nFile);
		cFile.delete();
	}
	
	public ConstraintWizardInfoWrapper readConstraintWrapper(String id){
		String nFile = "Constraint_" + id + ".constraint";
		File cFile = new File(dir + nFile);
		
		ConstraintWizardInfoWrapper wrapper = null;
		
		try {
			FileInputStream fis = new FileInputStream(cFile);
			ObjectInputStream os = new ObjectInputStream(fis);
			wrapper = (ConstraintWizardInfoWrapper)os.readObject();
			os.close();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} catch(ClassNotFoundException cnfEx){
			cnfEx.printStackTrace();
		}
		
		return wrapper;
	}
	
	public static void main(String ar[]){
		ConstraintInfo info = new ConstraintInfo();
		info.setDescription("Mas bla bla bla ... de la tales 3 (QUE MIERDA)");
		info.setId("0003");
		info.setName("Restricción de prueba número 3");
		info.setSignificance(0.8d);
		info.setStrategyCodeImplementation("public class Some3CARAJO{private Some3CARAJO(){}}");
		
		ConstraintInfo info2 = new ConstraintInfo();
		info2.setDescription("Mas bla bla bla ... y que carajos");
		info2.setId("0004");
		info2.setName("Test Constraint four");
		info2.setSignificance(0.1d);
		info2.setStrategyCodeImplementation(
					"public class Some4{" +
					"protected Some4(){"+
					"System.out.println(\"This is a test\")"+
					"}}");
		
		ArrayList<ConstraintInfo> list = new ArrayList<ConstraintInfo>();
		list.add(info);
		list.add(info2);
		
		XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
		
		writer.deleteConstraint("0003");
		
		System.out.println("File written");
		
	}
	
}
