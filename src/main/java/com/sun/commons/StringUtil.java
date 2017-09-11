package com.sun.commons;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static void main(String[] args) {
		String str = null;
		StringUtils.defaultIfBlank(str, "==");// null; "";" ";" "都会替换为默认值
		StringUtils.defaultIfEmpty(str, "**");// null;"";会替换为默认值
		StringUtils.defaultString(str, "$$");// null会替换为默认值，为防止字符串空指针
		StringUtils.defaultString(str);// null-->""

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
}
