package com.zelda.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * properties工具类
 * @author mantou
 */
public class PropertiesUtil {

	/**
	 * 默认的配置文件名
	 */
	private static final String FILE_NAME = "default.properties";

	private static final Properties PROPERTIES = new Properties();
	/**
	 * 获取配置文件中的配置
	 * @param key
	 * @return
	 */
	public static String getPropertiesValue(String key){
		try(FileReader fileReader = new FileReader(PropertiesUtil.class.getResource("/").getPath()+FILE_NAME)){
			PROPERTIES.load(fileReader);
		}catch (IOException e){
			e.printStackTrace();
		}
		return PROPERTIES.getProperty(key);
	}

}
