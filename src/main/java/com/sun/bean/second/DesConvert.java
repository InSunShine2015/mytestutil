package com.sun.bean.second;

import org.apache.commons.beanutils.Converter;


public class DesConvert implements Converter {

	public Object convert(Class type, Object value) {
        return toDes(type, value);
    }
	public Object toDes(Class type, Object value) {
		if (value == null || "".equals(value))
            return null;
        if (value instanceof String) {
            String dateValue = value.toString().trim();
            if (type.equals(com.sun.bean.second.Des.class)) {
            	Des d = new Des();
            	d.setDis("==="+dateValue+"=====");
            	return d;
            }
        }
		return null;
	}

	

	

}
