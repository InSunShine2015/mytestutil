package com.sun.commons.configure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
/**
 * 可以读取properties和xml文件的工具类
 * @author cheng jing
 *
 */
public class PropertiesUtil {
	public static Map<String, Object> configMap = new ConcurrentHashMap<String, Object>();

    public static String getStringValue(String fileName, String key) {
        if (!configMap.containsKey(key)) {
            PropertiesUtil.initConfig(fileName);
        }
        if (fileName.endsWith(".properties")) {
            PropertiesConfiguration cfg = (PropertiesConfiguration) configMap.get(fileName);
            return cfg.getString(key);
        } else if (fileName.endsWith(".xml")) {
            XMLConfiguration cfg = (XMLConfiguration) configMap.get(fileName);
            return cfg.getString(key);
        }
        return null;
    }

    private static void initConfig(String fileName) {
        try {
            if (fileName.endsWith(".xml")) {
                XMLConfiguration cfg = new XMLConfiguration(fileName);
                cfg.setReloadingStrategy(new FileChangedReloadingStrategy());
                configMap.put(fileName, cfg);
            } else if (fileName.endsWith(".properties")) {
                PropertiesConfiguration cfg = new PropertiesConfiguration(fileName);
                cfg.setReloadingStrategy(new FileChangedReloadingStrategy());
                configMap.put(fileName, cfg);
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
