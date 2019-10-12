package com.cache.sample.CacheImplementation.cache;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Description: Level1Cache class
 *
 */
public class Level1Cache {
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	private Level2Cache level2Cache;
	
	public Map<String, Object> level1Cache = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
	
	private int level1Size;	
	
	public void createLevel1Cache(int size) {
		level1Size = size;
	}
	
	public void setLevel2Cache() {
		level2Cache = new Level2Cache();
	}
	
	public void put(String key, Object value) {
        if(level1Cache.size() >= level1Size && level1Size > 0){
            Object oldValue = level1Cache.keySet().toArray()[0];
            //Remove the old entry when adding new item in the Cache
            remove((String)oldValue);
        }        
        try {
			level2Cache.put(key, value);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
        level1Cache.put(key, value);
    }

    public Object get(String key){
    	if(null == level1Cache.get(key)) {
			return level2Cache.get(key);
		}
		return level1Cache.get(key);
    }
    
    public void remove(String key){
    	level1Cache.remove(key);
    }

	public int getLevel1Size() {
		return level1Size;
	}

	public Map<String, Object> getLevel1Cache() {
		return level1Cache;
	}

	public void setLevel1Cache(Map<String, Object> level1Cache) {
		this.level1Cache = level1Cache;
	}
}