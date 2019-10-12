package com.cache.sample.CacheImplementation.service;

import com.cache.sample.CacheImplementation.cache.Level1Cache;

public class ServiceImpl implements Service {
	
	private Level1Cache level1Cache;
	
	public ServiceImpl() { }
	
	/**
	 * Create Level1Cache with the configured size and set Level2Cache
	 * @param level1CacheSize
	 */
	public ServiceImpl(int level1CacheSize) {
		level1Cache = new Level1Cache();
		level1Cache.createLevel1Cache(level1CacheSize);
		level1Cache.setLevel2Cache();
	}

	@Override
	public Object get(String key) {
		return level1Cache.get(key);
	}

	@Override
	public void put(String key, Object value) {
		level1Cache.put(key, value);
	}
}
