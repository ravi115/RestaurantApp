/*
 * (c) copyright 2017. 
 * This package provides implementation of XML configuration file.
 */
package com.mobile.restaurant.config;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * This class parse XML configuration file using apache library.
 * Exposes method to return values for given element name and attribute name. 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 */
public class Config {

	private String defaultName;
	protected XMLConfiguration config = null;
	
	/**
	 * Default constructor.
	 * Initializes configuration to read XML file.  
	 */
	public Config() {
		initialize();
	}
	/**
	 * Method to configure the XML file.
	 */
	private void initialize() {
		Configurations configs = new Configurations();
		try {
			config = configs.xml("appconfig.xml");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialize rootname Element of XML configuration file.
	 * @param nodeName rootname of the properties, you want to read.
	 */
	protected void setSettingName(final String nodeName) {
		defaultName = nodeName;
	}

	/**
	 * 
	 * @param propertyName property which value has to be read .
	 * @return complete path for 
	 */
	private String getCompleteElementName(final String propertyName) {
		String completeName = propertyName;
		if(null != defaultName && !defaultName.isEmpty() ) {
			completeName = String.format("%s.%s", defaultName, propertyName);
		}
		return completeName;
	}
	/**
	 * 
	 * @param propertyName
	 * @param key
	 * @return
	 */
	private String getCompleteAttributeName(final String propertyName, final String key) {
		String completeName = defaultName;
		String attributeName = "";
		if(null != completeName) {
			completeName = getCompleteElementName(propertyName);
		}
		attributeName = String.format("%s[@%s]", completeName,key);
		return attributeName;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getString(final String name) {
		String completeElementName = getCompleteElementName(name);
		return config.getString(completeElementName);
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public int getInteger(final String name) {
		String completeElementName = getCompleteElementName(name);
		return config.getInt(completeElementName);
	}
	/**
	 * 
	 * @param name
	 * @param key
	 * @return
	 */
	public String getAttributes(final String name, final String key) {
		String attributeName = getCompleteAttributeName(name, key);
		return config.getString(attributeName);
	}
}
