package com.sun.commons;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {
	/**
	 * 解析字符串日期为时间 ：只要字符串是在指定的日期格式中就可以解析
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateStr, "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss",
					"yyyy/MM/dd HH/mm/ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
