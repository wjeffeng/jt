package com.jt.common.util;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjToMapUtil {
	
	public static Map<String,String> transfer(Object obj){
		Map<String,String> map = new HashMap<>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object o = field.get(obj);
				map.put(field.getName(),o.toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
