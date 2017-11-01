package com.sun.guava;

import com.google.common.base.Optional;
/**
 * optional的测试类
 * @author sUN
 *
 */
public class OptionalTest {
	public static void main(String[] args) {
		//创建实例of的值不能为null值（对程序来说就是必传值,如果为null就会报错）
		Optional<String> op1 = Optional.of("123");
		//判断op1是否存在，常用于取值前的判断
		System.out.println(op1.isPresent());//true
		//取出实例中的值
		System.out.println(op1.get());//123
		
		System.out.println("==============op2==========");
		//创建实例absent表示什么都没有，此时如果用get会报错
		Optional<String> op2 = Optional.absent();
		//or表示当前面optional为不存在时，使用第二个optional表示的值
		System.out.println(op2.or(Optional.of("3333")).get());//3333
		//设置默认值
		System.out.println(op2.or("this is op2 default value"));//this is op2 default value
		
		System.out.println("=============op3===========");
		//创建实例，值可能是null也可能不是null,值不为null的实例
		Optional<String> op3 = Optional.fromNullable("ccc");
		//判断哦op3是否存在不为null的值
		System.out.println(op3.isPresent());//true
		//取值方式1 orNull方式
		System.out.println(op3.isPresent()? op3.orNull():op3.get());//ccc
		
		Optional<String> op4 = Optional.fromNullable(null);
		//判断op4是否存在不为null的值
		System.out.println(op4.isPresent());//false
		//取值方式1 orNull方式，结果是字符串null
		System.out.println(op4.isPresent()? op4.get():op4.orNull());//null
		//取值方式2 or方式，设置默认值
		System.out.println(op4.isPresent()? op4.get():op4.or("this is op4 default value"));//this is op4 default value
	}
}
