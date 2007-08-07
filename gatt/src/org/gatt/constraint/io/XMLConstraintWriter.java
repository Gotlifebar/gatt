package org.gatt.constraint.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.gatt.constraint.ConstraintInfo;
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
	
	
	private XMLConstraintWriter(){
		outp = new XMLOutputter();
		outp.setFormat(Format.getPrettyFormat());
	}
	
	public static XMLConstraintWriter getInstance(){
		if(instance == null)
			instance = new XMLConstraintWriter();
		
		return instance;
	}
	
	public void write(File file, ConstraintInfo cInfo){
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
		}catch(FileNotFoundException fEx){
			fEx.printStackTrace();
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	
	public static void main(String ar[]){
		ConstraintInfo info = new ConstraintInfo();
		info.setDescription("Mas bla bla bla ...");
		info.setId("0002");
		info.setName("Test Constraint two");
		info.setSignificance(0.8d);
		info.setStrategyCodeImplementation("public class Some2{public Some2(){}}");
		
		XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
		URI uri = null;
		try{
			uri = writer.getClass().getResource("/org/gatt/constraint/defined/ConstraintsDefinition.xml").toURI();
		}catch(URISyntaxException uriEx){
			uriEx.printStackTrace();
		}
		File file = new File(uri);
		writer.write(file,info);
		
		System.out.println("File written");
		
	}
	
}
