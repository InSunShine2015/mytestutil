package com.sun.base;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class MyStringUtil {
	/**
	 * 检测字符串是否在指定的范围内（用于参数检测）
	 * @param tocheckStr
	 * @param rangeStr
	 * @return
	 */
    public static boolean checkStringInRange(String tocheckStr,String[] rangeStr){
        boolean ret = false;
        if(StringUtils.isNotBlank(tocheckStr)&& ArrayUtils.contains(rangeStr,tocheckStr)){
            ret = true;
        }
        return ret;
   }
}
