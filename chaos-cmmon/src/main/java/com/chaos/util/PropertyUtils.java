package com.chaos.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class PropertyUtils {

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	private static PropertiesLoader loader = new PropertiesLoader("application.properties");
	
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
}
