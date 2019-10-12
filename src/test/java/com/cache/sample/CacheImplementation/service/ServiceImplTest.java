package com.cache.sample.CacheImplementation.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cache.sample.CacheImplementation.cache.Level1Cache;

@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest {
	
	@InjectMocks
	private ServiceImpl serviceImpl;
	
	@Mock
	private Level1Cache level1Cache;
	
	@Test
	public void testGetNull() {
		Mockito.when(level1Cache.get(Mockito.anyString())).thenReturn(null);
		String value = (String) serviceImpl.get("1");
		assertEquals(null, value);
	}
	
	@Test
	public void testGetValue() {
		Mockito.when(level1Cache.get(Mockito.anyString())).thenReturn("One");
		String value = (String) serviceImpl.get("1");
		assertEquals("One", value);
	}
	
	@Test
	public void TestPut() {
		Mockito.doNothing().when(level1Cache).put(Mockito.anyString(), Mockito.any());
		serviceImpl.put("1", "One");
	}

}
