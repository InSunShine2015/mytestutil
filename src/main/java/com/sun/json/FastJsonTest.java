package com.sun.json;

import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonTest {
	//测试fastjson属性注解
	public static void main(String[] args) {
		TestModel m = new TestModel();
		m.setDate(new Date());
		m.setName("aa");
		m.setAge(16);
		System.out.println(JSON.toJSONString(m,SerializerFeature.PrettyFormat));
	}
	
}
@JSONType(ignores= {"change"})
class TestModel{
	@JSONField(name="nAme")
	private String name;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date date;
	@JSONField(serialize=false)
	private Integer age;
	@JSONField(serialzeFeatures=SerializerFeature.WriteNullStringAsEmpty)
	private String other;

	private String change;
	
	
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
}