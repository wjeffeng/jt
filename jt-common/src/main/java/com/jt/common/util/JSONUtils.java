package com.jt.common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {
	
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	
	/**
	 * JSON日期格式化
	 * @param data
	 * @return
	 */
	public static JSON dateFormat(List<? extends Object> data) {
		return dateFormat(data,"");
	}
	
	/**
	 * JSON日期格式化
	 * @param data
	 * @param dateFormat
	 * @return
	 */
	public static JSON dateFormat(List<? extends Object> data, String dateFormat) {
		if (data == null || data.isEmpty()) {
			return new JSONArray();
		}
		if(dateFormat.isEmpty()){
			JSON.DEFFAULT_DATE_FORMAT = YYYY_MM_DD_HH_MM;
		}else{
			JSON.DEFFAULT_DATE_FORMAT = dateFormat;
		}
		return JSON.parseArray(JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat));
	}
}
