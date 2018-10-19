package com.sun.base.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 借鉴mybatis中枚举的写法
 * @author cheng jing
 *
 */
public enum Fruit {
	
	APP(FType.APPLE),
	ORANGE(FType.ORANGE),
	LEMON(FType.LEMON);
	
	public final int TYPE_CODE;
	Fruit(int code){
		this.TYPE_CODE = code;
	}
	
	
	private static Map<Integer,Fruit> codeLookup = new HashMap<>();
	static {
	    for (Fruit type : Fruit.values()) {
	    	codeLookup.put(type.TYPE_CODE, type);
	    }
	}
	
	public static Fruit forCode(int code)  {
	    return codeLookup.get(code);
	}
	
	public static boolean existCode(int code) {
		return codeLookup.containsKey(code);
	}
}
