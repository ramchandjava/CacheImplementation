package com.cache.sample.CacheImplementation.cache;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Level1CacheTest {
	
	@InjectMocks
	private Level1Cache level1Cache;
	
	@Mock
	private Level2Cache level2Cache;
	
	@Before
	public void setup() {		
		Map<String, Object> level1CacheMap = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
		level1CacheMap.put("1", "One");
		level1CacheMap.put("2", "Two");
		level1CacheMap.put("10", "Ten");
		level1Cache.setLevel1Cache(level1CacheMap);
		
	}
	
	@Test
	public void testPutSizeZero() throws IOException {
		level1Cache.createLevel1Cache(0);
		Mockito.doNothing().when(level2Cache).put(Mockito.anyString(), Mockito.any());
	    level1Cache.put("1", "One");
	}	
	
	@Test
	public void testPutSize() throws IOException {
		level1Cache.createLevel1Cache(2);
		Mockito.doNothing().when(level2Cache).put(Mockito.anyString(), Mockito.any());
	    level1Cache.put("1", "One");
	}
	
	@Test
	public void testPutSizeException() throws IOException {
		level1Cache.createLevel1Cache(3);
		Mockito.doThrow(new IOException()).when(level2Cache).put(Mockito.anyString(), Mockito.any());
	    level1Cache.put("1", "One");
	}
	
	@Test
	public void testGetLevel2Cache() throws IOException {
		level1Cache.createLevel1Cache(3);
	    String value = (String)level1Cache.get("10");
	    assertEquals("Ten", value);
	}
	
	@Test
	public void testGetLevel1Cache() throws IOException {
		level1Cache.createLevel1Cache(3);
		Mockito.when(level2Cache.get(Mockito.anyString())).thenReturn("Ten");
	    String value = (String)level1Cache.get("10");
	    assertEquals("Ten", value);
	}

}
