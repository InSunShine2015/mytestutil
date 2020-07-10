package com.sun.commons;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
	/**
	 * LocalDate转为Date
	 * @param localDate
	 * @return
	 */
	public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
	/**
	 * localDateTime 转为Date
	 * @param localDateTime
	 * @return
	 */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * Date转为LocalDate
     * @param date
     * @return
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    /**
     * Date转为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
