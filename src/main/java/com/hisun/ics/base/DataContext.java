package com.hisun.ics.base;

import java.util.concurrent.ConcurrentHashMap;

public class DataContext {
	private ConcurrentHashMap values = new ConcurrentHashMap();

	public void put(String name, Object value) {
		values.put(name, value);
	}

	public Object get(String name) {
		return values.get(name);
	}

	public String getValue(String name) {
		return (String) get(name);
	}
	
}
