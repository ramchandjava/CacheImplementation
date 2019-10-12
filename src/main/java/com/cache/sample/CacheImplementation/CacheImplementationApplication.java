package com.cache.sample.CacheImplementation;

import java.util.logging.Logger;

import com.cache.sample.CacheImplementation.service.Service;
import com.cache.sample.CacheImplementation.service.ServiceImpl;

/**
 * 
 * Description: Startup class
 *
 */
public class CacheImplementationApplication {
	
	private static Logger log = Logger.getLogger(CacheImplementationApplication.class.getName());
	
	public static void main(String[] args) {
		Service service = new ServiceImpl(10);
		service.put("20", "Twenty");
		log.info(service.get("20").toString());
		service.put("21", "TwentyOne");
		log.info(service.get("21").toString());
	}
}
