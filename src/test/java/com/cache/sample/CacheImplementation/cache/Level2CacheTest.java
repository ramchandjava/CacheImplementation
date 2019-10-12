package com.cache.sample.CacheImplementation.cache;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Level2CacheTest {
	
	@InjectMocks
	private Level2Cache level2Cache;
	
	@Mock
	private Properties properties;
	
	@Test
	public void testPut() throws IOException {
		Mockito.when(properties.setProperty(Mockito.anyString(), Mockito.anyString())).thenReturn(null );
		Mockito.doNothing().when(properties).store(Mockito.any(FileOutputStream.class), Mockito.anyString());
	    level2Cache.put("1", "Two");
	}
	
	@Test(expected = IOException.class)
	public void testPutException() throws IOException {
		Mockito.when(properties.setProperty(Mockito.anyString(), Mockito.anyString())).thenReturn(null );
		Mockito.doThrow(new IOException()).when(properties).store(Mockito.any(FileOutputStream.class), Mockito.anyString());
	    level2Cache.put("1", "Two");
	}
	
	@Test
	public void getGetNull() throws IOException {
		Mockito.when(properties.getProperty(Mockito.anyString())).thenReturn(null);
	    String value = (String) level2Cache.get("1");
	    assertEquals(null, value);
	}
}
