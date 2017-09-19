package com.sun.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static void main(String[] args) {
		// testStr();
		// strToArray();
		ArrayToString();
	}

	/**
	 * 字符串默认值
	 */
	public static void testStr() {
		String str = null;
		StringUtils.defaultIfBlank(str, "==");// null; "";" ";" "都会替换为默认值
		StringUtils.defaultIfEmpty(str, "**");// null;"";会替换为默认值
		StringUtils.defaultString(str, "$$");// null会替换为默认值，为防止字符串空指针
		StringUtils.defaultString(str);// null-->""
	}

	/**
	 * 字符串分隔为数组
	 */
	public static void strToArray() {
		// 分隔字符串为数组
		// 即使分隔符中间无值也算作 对api的分隔有很好作用
		String tosplit = "a|b|||";
		String[] toArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(tosplit, "|");// --a
																							// b
																							// 空串
																							// 空串
																							// 空串
		for (String st : toArray) {
			System.out.println("-->" + st + "<--");
		}
		String[] toArray1 = tosplit.split("\\|");// --a b
		for (String st : toArray1) {
			System.out.println("==>" + st + "<==");
		}

	}

	public static void ArrayToString() {
		String[] stra = { "a", "b" };
		System.out.println(ArrayUtils.toString(stra));
		// 含有null
		String[] strNnull = new String[] { "c", null, "d" };
		System.out.println(ArrayUtils.toString(strNnull));// null显示为<null>

		String[] stra1 = null;
		System.out.println(ArrayUtils.toString(stra1, "empty array"));// 当数组为null时显示的字符串

		// list转为数组输出
		List<String> list = new ArrayList<String>();
		list.add(null);
		list.add("aa");

		System.out.println(ArrayUtils.toString(list.toArray()));

		// 克隆与反转 数组中含有null元素时不会抛出异常
		long[] array1 = { 1, 3, 5, 9, 4 };
		long[] array2 = ArrayUtils.clone(array1);
		ArrayUtils.reverse(array2);
		System.out.println("原来数组：" + ArrayUtils.toString(array1) + ";现在数组：" + ArrayUtils.toString(array2));
	}
}
