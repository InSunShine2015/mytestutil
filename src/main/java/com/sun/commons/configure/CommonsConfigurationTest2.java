package com.sun.commons.configure;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class CommonsConfigurationTest2 {
	public static void main(String[] args) {
		for(int i = 0;i<1000;i++) {
			
	        String url = getStringValue("database.url");
	        System.out.println("url:" + url);
	        int port =getIntValue("database.port");
	        System.out.println("port:"+port);
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
		System.out.println(this.getClass().getClassLoader().getResource("test.xml"));
	}
	public static final String fileName = "test.xml";

    public static XMLConfiguration cfg = null;

    static {
        try {
            cfg = new XMLConfiguration(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        // 配置文件 发生变化就重新加载
        cfg.setReloadingStrategy(new FileChangedReloadingStrategy());
    }

    public static String getStringValue(String key) {
        return cfg.getString(key);
    }

    public static int getIntValue(String key) {
        return cfg.getInt(key);
    }
}
