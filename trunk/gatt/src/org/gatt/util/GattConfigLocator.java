package org.gatt.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigLocatorIF;

public class GattConfigLocator implements JFigLocatorIF {
	
	private String configFileName;
	
	private String configLocation;
	
	private String configDirectory = "config";
	
	private String defaultConfigFileName = "config.xml";
	
	public GattConfigLocator(String fileName, String directory){
		this.configFileName = fileName;
		this.configLocation = System.getProperties().getProperty("user.dir");
		this.configDirectory = directory;
	}
	
	public String getConfigFileName() {
		return configFileName;
	}

	public String getConfigLocation() throws JFigException {
		return configLocation;
	}

	public String getConfigDirectory() {
		return configDirectory;
	}
	
	public String getDefaultConfigFileName() {
		return defaultConfigFileName;
	}

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

	public void setConfigFileName(String arg0) {
		this.configFileName = arg0;
	}

	public void setConfigLocation(String arg0) throws JFigException {
		this.setConfigLocation(arg0);
	}

	public void setDefaultConfigFileName(String arg0) {
		this.defaultConfigFileName = arg0;
	}
}
