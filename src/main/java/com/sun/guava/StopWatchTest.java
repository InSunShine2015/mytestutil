package com.sun.guava;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Stopwatch;
import com.sun.log.Log4j2Client;
/**
 * StopWatch可以用来做程序中的计时器
 * 使用场景：
 * 	有时候会在程序中记录操作耗时，比如在进入某个方法后，计算当前时间，当执行完某个方法，计算当前时间，两个时间的差就是程序执行的耗时时间
 *  如果采用StopWatch就会比较优雅的实现这一功能
 * @author sUN
 *
 */
public class StopWatchTest {
	private static final Logger log = LogManager.getLogger(StopWatchTest.class);
	public static void main(String[] args) {
		//创建计时器，并开始计时
		Stopwatch sw = Stopwatch.createStarted();
		try {
			//做某些耗时的操作
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//停止计时器
		sw.stop();
		long last1 = sw.elapsed(TimeUnit.MILLISECONDS);
		//计时时间
		System.out.println(last1+"==="+sw);
		log.info("程序执行到此耗时："+sw);
		//开始计时
		sw.start();
		long last2 = sw.elapsed(TimeUnit.MILLISECONDS);
		System.out.println(last2+"==="+sw);
		log.info("程序执行到此耗时："+sw);
		//计时器重置
		sw.reset();
		//计时器开始
		sw.start();
		try {
			Thread.currentThread().sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//重置后的计时器计时时间
		long last3 = sw.elapsed(TimeUnit.MILLISECONDS);
		System.out.println(last3+"==="+sw);
		log.info("程序执行到此耗时："+sw);
	}
}
