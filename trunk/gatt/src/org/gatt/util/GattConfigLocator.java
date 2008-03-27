package org.gatt.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigLocatorIF;
/**
 * 
 * @author Andrés
 *
 */
public class GattConfigLocator implements JFigLocatorIF {
	/**
	 * It stores the name of the configuration file
	 */
	private String configFileName;
	/**
	 * It stores the path where the config file is located
	 */
	private String configLocation;
	/**
	 * It stores the name of the configuration directory
	 */
	private String configDirectory = "config";
	/**
	 * The default name for the configuration file
	 */
	private String defaultConfigFileName = "config.xml";
	/**
	 * Constructs an object from the GattConfigLocator class
	 * @param fileName The name of the configuration file
	 * @param directory the name of the directory where the configuration file is stored
	 */
	public GattConfigLocator(String fileName, String directory){
		this.configFileName = fileName;
		this.configLocation = System.getProperties().getProperty("user.dir");
		this.configDirectory = directory;
	}
	/**
	 * Gets the configuration file name
	 * @return String with the configuration file name 
	 */
	public String getConfigFileName() {
		return configFileName;
	}
	/**
	 * return the location of the configuration file
	 */
	public String getConfigLocation() throws JFigException {
		return configLocation;
	}
	/**
	 * return the name of the directory in which the configuration file is stored
	 */
	public String getConfigDirectory() {
		return configDirectory;
	}
	/**
	 * return the default name of the configuration file
	 */
	public String getDefaultConfigFileName() {
		return defaultConfigFileName;
	}
	/**
	 * return the InputStream for the configuration file
	 */
	public InputStream getInputStream() throws JFigException {
		InputStream inputStream = null;
		String path = getConfigLocation() + "\\" + getConfigDirectory() + "\\" + getConfigFileName();
		try{
			inputStream = new FileInputStream(path);
		}catch(FileNotFoundException ex){
			throw new JFigException();
		}
		return inputStream;
	}
	/**
	 * sets the config file name
	 */
	public void setConfigFileName(String arg0) {
		this.configFileName = arg0;
	}
	/**
	 * sets the configuration file location
	 */
	public void setConfigLocation(String arg0) throws JFigException {
		this.setConfigLocation(arg0);
	}
	/**
	 * sets the default configuration file name
	 */
	public void setDefaultConfigFileName(String arg0) {
		this.defaultConfigFileName = arg0;
	}
}
