package com.sun.guava;

import com.google.common.base.Verify;
/**
 * Verify可用于校验参数
 * 两种：校验object本身是否为null
 * 	         校验表达式是否为null
 * @author sUN
 *
 */
public class VerifyTest {
	public static void main(String[] args) {
//		Verify.verify("3".equals("5"));//验证表达式必须为true，否则会抛出异常
		Verify.verify("3".equals("3"));
		
		//使用自定义错误模板，模板中的值可以是基本类型，也可以是object
		try {
//			Verify.verify("3".equals("5"), "参数%s与%s不相同", "3","5");
			Verify.verify(3+5<0, "参数%s与%s的和不小于0", 3,5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String str = null;
		String str1 = "mytestString";
		String result = null;
		try {
//			result = Verify.verifyNotNull(str,"字符串%s为null",str);
			result = Verify.verifyNotNull(str1, "字符串%s为null",str1);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
