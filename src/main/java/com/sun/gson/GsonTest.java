package com.sun.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * 测试bean转换json字符串时忽略部分属性 1. 属性注释 2. builder 规则重写 3. builder创建gson 4.
 * gson处理对象的序列化与反序列化
 * 
 * @author sUN
 *
 */
public class GsonTest {
	private int id;

	// step1 加注解
	@Expose(serialize = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static void main(String[] args) {
		GsonTest t = new GsonTest();
		t.setName("test");
		t.setId(12);

		// builder
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");// 日期格式化
		builder.serializeSpecialFloatingPointValues();
		builder.addSerializationExclusionStrategy(new ExclusionStrategy() {

			public boolean shouldSkipField(FieldAttributes fieldAttributes) {
				final Expose expose = fieldAttributes.getAnnotation(Expose.class);
				return expose != null && !expose.serialize();
			}

			public boolean shouldSkipClass(Class<?> aClass) {
				return false;
			}
		}).addDeserializationExclusionStrategy(new ExclusionStrategy() {

			public boolean shouldSkipField(FieldAttributes fieldAttributes) {
				final Expose expose = fieldAttributes.getAnnotation(Expose.class);
				return expose != null && !expose.deserialize();
			}

			public boolean shouldSkipClass(Class<?> aClass) {
				return false;
			}
		});

		Gson gson = builder.create();
		String str = gson.toJson(t);
		System.out.println(str);
	}
}
