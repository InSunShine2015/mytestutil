package com.sun.bean.second;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;


/**
 * 测试
 * @author cheng jing
 *
 */
public class ConvertTest {
	public static void main(String[] args) {
		Modle m = new Modle();
		m.setF1(1);
		m.setF2(2L);
		m.setF3(3d);
		m.setF4(new Date());
		Map<String, String> map = BeanConvertUtils.bean2Map(m);
		System.out.println(JSON.toJSONString(map));
		
		Map<String,Object> map1 = new HashMap<>();
		map1.put("f1", "1");
		map1.put("f2", "2");
		map1.put("f3", "3");
		map1.put("f4", "2018-10-18");
		map1.put("f5", "string");
		map1.put("f6", "hi");
		System.out.println(BeanConvertUtils.map2Bean(map1, Modle.class));
		
		Modle m1 = new Modle();
		BeanConvertUtils.copyProperties(m1, m);
		System.out.println(JSON.toJSONString(m1));
	}
}
