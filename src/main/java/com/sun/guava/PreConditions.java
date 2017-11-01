package com.sun.guava;

import java.util.Date;

import com.google.common.base.Preconditions;

public class PreConditions {
	public static void main(String[] args) {
		
		//检查参数是否符合某种规则
		Preconditions.checkArgument(3>2);
		
		String str = null;
		try {
			Preconditions.checkArgument(str!=null, "argument should not be null");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String str1 = "java";
		try {
			
			Preconditions.checkArgument(str1.startsWith("J"), "the String \" %s \" should start with Upper Case", str1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String str2 = "aawewdfff";
		try {
			Preconditions.checkElementIndex(str2.indexOf("z"), 8);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//检查空引用
		Date date = null;
		try {
			Preconditions.checkNotNull(date, "%s should not be null","日期");
			Preconditions.checkNotNull(date);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
