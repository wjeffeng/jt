package com.jt.common.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Param implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6892084933027679374L;
	
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	public void addParam(String fieldName, Object fieldValue) {
		paramMap.put(fieldName, fieldValue);
	}

	public void removeParam(String fieldName) {
		paramMap.remove(fieldName);
	}

	public void clearParam() {
		paramMap.clear();
	}

	public Object getParam(String fieldName) {
		return paramMap.get(fieldName);
	}
}
