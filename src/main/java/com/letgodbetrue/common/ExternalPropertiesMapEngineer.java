package com.letgodbetrue.common;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Properties file mapping technician of files via a resource stream.
 *
 * @author Benjamin F. Paige III
 * @since Jan 19, 2019
 */
public final class ExternalPropertiesMapEngineer implements MapEngineer<String, String>{

	private String propertiesFileName;
	private Properties properties;

	public ExternalPropertiesMapEngineer(String fileName) {
		this.propertiesFileName = fileName;
		this.properties = new Properties();
	}

	@Override
	public String value(String key) throws Exception {
		properties.load(new FileInputStream(propertiesFileName));
		return properties.getProperty(key);
	}
}
