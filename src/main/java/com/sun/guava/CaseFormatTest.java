package com.sun.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

public class CaseFormatTest {

	public static void main(String[] args) {
		//将小写驼峰式 改写成小写下划线式
		Converter<String, String> convert0 = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN);
		String result0 = convert0.convert("javaLearn");
		System.out.println(result0);
		//将大写驼峰式改写成小写下划线式
		Converter<String, String> convert1 = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN);
		String result1 = convert1.convert("JavaLearn");
		System.out.println(result1);
		
		Converter<String, String> convert2 = CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_HYPHEN);
		String result2 = convert2.convert("JAVA_LEARN");
		System.out.println(result2);
		
		String result3 =  CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "javaLearn");
		System.out.println(result3);
		
		CaseFormat format = CaseFormat.valueOf("LOWER_CAMEL");
		System.out.println(format.name());
	}
}
