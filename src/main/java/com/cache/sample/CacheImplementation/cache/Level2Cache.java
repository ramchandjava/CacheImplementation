package com.cache.sample.CacheImplementation.cache;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Description: Level2Cache class
 *
 */
public class Level2Cache {
	
	private Properties properties;
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	public Level2Cache() {
		properties = new Properties();
        try {
        	//Read from properties file
            properties.load(new FileInputStream("Level2File.properties"));
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
	}
	
	public void put(String key, Object value) throws IOException{
        properties.setProperty(key, value.toString());
        //Write to Properties file
        properties.store(new FileOutputStream("Level2File.properties"), null);
    }

    public Object get(String key){
    	return properties.get(key);
    }

}
