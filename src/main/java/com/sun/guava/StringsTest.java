package com.sun.guava;

import com.google.common.base.Strings;
/**
 * guava的Strings类提供了对String的一些常用静态方法操作
 * 相对commons的string工具来说api要少
 * @author sUN
 *
 */
public class StringsTest {
	public static void main(String[] args) {
		//null和空字符串转换
		System.out.println("##"+Strings.nullToEmpty(null)+"##");
		System.out.println("##"+Strings.emptyToNull("")+"##");
		
		//重复字符串
		System.out.println("##"+Strings.repeat("03", 3)+"##");
		
		//长度不足前或后补足指定字符
		System.out.println("##"+Strings.padStart("1101", 8, '0')+"##");
		System.out.println("##"+Strings.padEnd("1101", 8, '$')+"##");
		
		//查找两个字符串相同的前缀或后缀
		System.out.println("##"+Strings.commonPrefix("abd", "abe")+"##");
		System.out.println("##"+Strings.commonSuffix("abde121de2", "se2323de2")+"##");
	}
}
