package com.sun.commons;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static void main(String[] args) {
		String str = null;
		StringUtils.defaultIfBlank(str, "==");// null; "";" ";" "都会替换为默认值
		StringUtils.defaultIfEmpty(str, "**");// null;"";会替换为默认值
		StringUtils.defaultString(str, "$$");// null会替换为默认值，为防止字符串空指针
		StringUtils.defaultString(str);// null-->""

	}
}
