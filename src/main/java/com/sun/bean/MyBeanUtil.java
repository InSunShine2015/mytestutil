package com.sun.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MyBeanUtil {
	/**
	 * 将Map值转换为目的bean
	 * @param bean
	 * @param props
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static <T> T convertMapToBean(T bean,Map props) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.populate(bean, props);
		return bean;
	}
	/**
	 * 拷贝bean的属性值到目的bean的属性值
	 * @param fromBean
	 * @param toBean
	 * @param fromProps
	 * @param toProps
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void copyValuesFromBeanToBean(Object fromBean,Object toBean,String[] fromProps,String[] toProps) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(fromBean == null || toBean == null || fromProps == null || toProps == null || fromProps.length != toProps.length){
			throw new IllegalArgumentException("传入参数不可以为空，拷贝属性数量必须相同");
		}
		int length = fromProps.length;
		for(int i=0;i<length;i++){
			String fromValue = BeanUtils.getProperty(fromBean, fromProps[i]);
			BeanUtils.setProperty(toBean, toProps[i], fromValue);
		}
	}
}
