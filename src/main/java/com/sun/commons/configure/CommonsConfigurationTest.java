package com.sun.commons.configure;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class CommonsConfigurationTest {
	public static void main(String[] args) {
		for(int i = 0;i<1000;i++) {
			
			String name = getStringValue("name");
			System.out.println(name);
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		CommonsConfigurationTest tet = new CommonsConfigurationTest();
//		tet.test();
	}
	private void test() {
		System.out.println(this.getClass().getClassLoader().getResource("icp.properties"));
	}
	public static final String fileName = "ice.properties";

    public static PropertiesConfiguration cfg = null;

    static {
        try {
            cfg = new PropertiesConfiguration(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        // 当文件的内容发生改变时，配置对象也会刷新
        cfg.setReloadingStrategy(new FileChangedReloadingStrategy());
    }
    // 读String
    public static String getStringValue(String key) {
        return cfg.getString(key);
    }
    // 读int
    public static int getIntValue(String key) {
        return cfg.getInt(key);
    }
    // 读boolean
    public static boolean getBooleanValue(String key) {
        return cfg.getBoolean(key);
    }
    // 读List
    public static List<?> getListValue(String key) {
        return cfg.getList(key);
    }
    // 读数组
    public static String[] getArrayValue(String key) {
        return cfg.getStringArray(key);
    }
}
