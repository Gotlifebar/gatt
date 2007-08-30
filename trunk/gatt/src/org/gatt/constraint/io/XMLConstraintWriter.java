package org.gatt.constraint.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gatt.constraint.ConstraintInfo;
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
	protected final String ID_ATTR = "id";
	protected final String NAME_ATTR = "name";
	protected final String SIGNIFICANCE_ATTR = "significance";
	protected final String INDENT = "     ";
	
	public static XMLConstraintWriter instance;
	private XMLOutputter outp;
	private File file;
	
	
	private XMLConstraintWriter(){
		
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);

		outp = new XMLOutputter();
		outp.setFormat(Format.getPrettyFormat());
		
		//URI uri = null;
		String sFile = null;
		try{
			sFile = config.getValue("XMLWriterInfo", "FilePath");
			//String sFile = config.getValue("XMLWriterInfo", "FilePath");
			//System.out.println(sFile);
			//uri = getClass().getResource(sFile).toURI();
		}/*catch(URISyntaxException uriEx){
			uriEx.printStackTrace();
		}*/catch(JFigException jFigEx){
			jFigEx.printStackTrace();
		}
		
		file = new File(sFile);
		//file = new File(sFile);
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
		description.setText(cInfo.getDescription());
		
		Element impl = new Element(IMPLEMENTATION_ELEMENT);
		impl.addContent(new CDATA(cInfo.getStrategyCodeImplementation()));
		
		constraint.addContent(description);
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
			description.setText(cInfo.getDescription());
			
			Element impl = new Element(IMPLEMENTATION_ELEMENT);
			impl.addContent(new CDATA(cInfo.getStrategyCodeImplementation()));
			
			constraint.addContent(description);
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
		description.setText(cInfo.getDescription());
		
		Element impl = new Element(IMPLEMENTATION_ELEMENT);
		impl.addContent(new CDATA(cInfo.getStrategyCodeImplementation()));
		c.addContent(description);
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
