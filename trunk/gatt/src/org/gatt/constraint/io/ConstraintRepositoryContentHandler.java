package org.gatt.constraint.io;

import java.util.Vector;

import org.gatt.constraint.ConstraintInfo;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.LocatorImpl;


public class ConstraintRepositoryContentHandler extends DefaultHandler {	
	private Vector<ConstraintInfo> constraints;	
	private ConstraintInfo cInfo;
	private Locator locator;
	private int currentTag;
	
	private static final int TAG_UNDEFINED = -1,
							 TAG_CONSTRAINTS = 0,
							 TAG_CONSTRAINT = 1,
							 TAG_DESCRIPTION = 2,
							 TAG_SIGNIFICANCE = 3,
							 TAG_IMPLEMENTATION = 4,
							 TAG_NAME = 5;
	
	private  static String[] TAG_LABEL = {
								"Constraints",
								"Constraint",
								"Description",
								"Significance",
								"Implementation",
								"Name"
							 };
	
	public ConstraintRepositoryContentHandler(){
		locator = new LocatorImpl();
		this.setDocumentLocator(locator);
	}
	
	private static int obtainTag(String tag){
		for(int i = 0; i < TAG_LABEL.length; i++)
			if( TAG_LABEL[i].compareTo(tag) == 0 )
				return i;
		return TAG_UNDEFINED;
	}
	
	public void startDocument() throws SAXException {
		constraints = new Vector<ConstraintInfo>();
		currentTag = TAG_UNDEFINED;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(currentTag = obtainTag(localName)){
			case TAG_CONSTRAINT:
				cInfo = new ConstraintInfo();
				cInfo.setId(attributes.getValue("id"));
				break;
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		switch(currentTag){
			case TAG_DESCRIPTION:
				cInfo.setDescription(str);
				break;
			case TAG_SIGNIFICANCE:
				try{
					cInfo.setSignificance(Double.parseDouble(str));
				}catch(NumberFormatException nfe){
					throw new SAXParseException("Not valid Significance value",locator,nfe);
				}
				break;
			case TAG_IMPLEMENTATION:
				cInfo.setStrategyCodeImplementation(str);
				break;
			case TAG_NAME:
				cInfo.setName(str);
				break;
		}
	}	

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch(currentTag = obtainTag(localName)){
			case TAG_CONSTRAINT:
				constraints.add(cInfo);
				break;
		}
	}
	public Vector<ConstraintInfo> getConstraints(){
		return constraints;
	}

	@Override
	public void error(SAXParseException spe) throws SAXException {
		throw spe;
	}

	@Override
	public void fatalError(SAXParseException spe) throws SAXException {
		throw spe;
	}

	@Override
	public void warning(SAXParseException spe) throws SAXException {
		throw spe;
	}
}
