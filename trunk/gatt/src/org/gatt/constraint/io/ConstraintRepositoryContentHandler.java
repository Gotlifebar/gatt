package org.gatt.constraint.io;

import java.util.HashMap;

import org.gatt.constraint.ConstraintInfo;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.LocatorImpl;


/**
 * This class handles the events produced by a java SAX parser and serves as a repository of the loaded 
 * ConstraintInfo objects located into the xml file parsed.
 * @author David
 *
 */
public class ConstraintRepositoryContentHandler extends DefaultHandler {
	
	/**
	 * Loaded constraints
	 */
	private HashMap<String,ConstraintInfo> constraints;
	
	/**
	 * Current constraint that is being loaded
	 */
	private ConstraintInfo cInfo;
	
	/**
	 * Locator inside the xml document
	 */
	private Locator locator;
	
	/**
	 * Current tag being parsed
	 */
	private int currentTag;
	
	/**
	 * Codes of the valid tags in the document
	 */
	private static final int TAG_UNDEFINED = -1,
							 TAG_CONSTRAINTS = 0,
							 TAG_CONSTRAINT = 1,
							 TAG_DESCRIPTION = 2,							 
							 TAG_IMPLEMENTATION = 3,
							 TAG_DEFINITION = 4;
							 
	
	/**
	 * Valid tags in the document
	 */
	private  static String[] TAG_LABEL = {
								"constraints",
								"constraint",
								"description",								
								"implementation",
								"definition"
							 };
	
	/**
	 * Constructor
	 */
	public ConstraintRepositoryContentHandler(){
		locator = new LocatorImpl();
		this.setDocumentLocator(locator);
	}
	
	/** Returns the tag code given the tag string
	 * @param tag tag string
	 * @return the tag code for the tag string
	 */
	private static int obtainTag(String tag){
		for(int i = 0; i < TAG_LABEL.length; i++)
			if( TAG_LABEL[i].compareToIgnoreCase(tag) == 0 )
				return i;
		return TAG_UNDEFINED;
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	public void startDocument() throws SAXException {
		constraints = new HashMap<String, ConstraintInfo>();
		currentTag = TAG_UNDEFINED;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {		
		switch(currentTag = obtainTag(qName)){
			case TAG_CONSTRAINT:				
				cInfo = new ConstraintInfo();
				cInfo.setId(attributes.getValue("id"));
				try{
					cInfo.setSignificance(Double.parseDouble(attributes.getValue("significance")));
				}catch(NumberFormatException nfe){
					throw new SAXParseException("Not valid Significance value",locator,nfe);
				}
				cInfo.setName(attributes.getValue("name"));
				break;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);		
		switch(currentTag){
			case TAG_DESCRIPTION:
				cInfo.setDescription(str);
				break;			
			case TAG_IMPLEMENTATION:
				cInfo.setStrategyCodeImplementation(str);
				//System.out.println("IMPL: " + str);
				break;
			case TAG_DEFINITION:
				cInfo.setDefinition(str);
				break;
		}
	}	

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		currentTag = TAG_UNDEFINED;
		switch( obtainTag(qName)){
			case TAG_CONSTRAINT:
				constraints.put(cInfo.getId(), cInfo);
				//constraints.add(cInfo);
				break;
		}
	}
	/** Return the loaded constraints
	 * @return the loaded constraints
	 */
	public HashMap<String, ConstraintInfo> getConstraints(){
		return constraints;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
	 */
	@Override
	public void error(SAXParseException spe) throws SAXException {
		throw spe;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	@Override
	public void fatalError(SAXParseException spe) throws SAXException {
		throw spe;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
	 */
	@Override
	public void warning(SAXParseException spe) throws SAXException {
		throw spe;
	}
}
