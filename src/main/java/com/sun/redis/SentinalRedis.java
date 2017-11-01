package com.sun.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 *集成spring
 *1 
 *<bean id="propertyPlaceholder" class="org.springframework,beans.factory.config.PropertyPlaceholderConfigurer">
 *	<property name="location">
 *		<list>
 *			<value>classpath:/redis.properties</value>
 *		</list>
 *  </property>
 *</bean>
 *<bean id="sentinalRedis" class="当前类所在全路径" init-method="init" destroy-method="destroy">
 *	<property name="masterName" value="${redis.masterName}"/>
 *	<property name="masterPassword" value="${redis.masterPassword}"/>
 *	<property name="timeOut" value="${redis.timeOut}"/>
 *	<property name="databaseNo" value="${redis.databaseNo}"/>
 *	<property name="maxTotal" value="${redis.pool.maxTotal}"/>
 *	<property name="maxIdle" value="${redis.pool.maxIdle}"/>
 *	<property name="testOnBorrow" value="${redis.pool.testOnBorrow}"/>
 *	<property name="blockWhenExhausted" value="${redis.pool.blockWhenExhausted}"/>
 *	<property name="maxWaitMills" value="${redis.pool.maxWaitMillis}"/>
 *  <property name="sentinelIp" value="${redis.sentinelIp}"/>
 *  <property name="sentinelPort" value="${redis.sentinelPort}"/>
 *</bean>
 * 
 * @author sUN
 *
 */
public class SentinalRedis {
	private static final Logger log = LogManager.getLogger(SentinalRedis.class);
	/** 主redis名 */
	private static String masterName;
	/** 主redis密码 */
	private static String masterPassword;
	/** 超时时间 */
	private static Integer timeOut;
	/** 数据库编号 */
	private static Integer databaseNo;
	/** 最大实例数 */
	private static Integer maxTotal;
	/** 最大空闲数 */
	private static Integer maxIdle;
	/** 取出是否验证实例有效 */
	private static Boolean testOnBorrow;
	/** 耗尽时是否阻塞 */
	private static Boolean blockWhenExhausted;
	/** 最长等待时间 */
	private static Integer maxWaitMills;
	/** 哨兵ip */
	private static String sentinelIp;
	/** 哨兵端口 */
	private static Integer sentinelPort;

	private static JedisSentinelPool pool;

	private HostAndPort hp;

	public SentinalRedis() {
	}

	public void init() {
		log.info("init Redis sentinel Pool....");
		Set<String> sentinels = new HashSet<String>();
		String[] ips = sentinelIp.split(",");
		for (String ip : ips) {//可以添加多个哨兵
			sentinels.add(new HostAndPort(ip, sentinelPort).toString());
		}
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setBlockWhenExhausted(blockWhenExhausted);
		config.setMaxWaitMillis(maxWaitMills);
		config.setTestOnBorrow(testOnBorrow);

		pool = new JedisSentinelPool(masterName, sentinels, config, timeOut, masterPassword, databaseNo);

		HostAndPort hp = pool.getCurrentHostMaster();
		this.hp = hp;
		log.info("load redis sentinal SUCCESS:current master info[host]" + hp.getHost() + " [port] " + hp.getPort());
		// 测试Redis是否正常
		log.info("test redis link .... now will put key will be test and value will be testValue");
		setString("test", "testValue");
		log.info("now will get key's value:" + getString("test"));
		delString("test");
		log.info("now will get key's value after del the key" + getString("test") + "---");
		log.info("test redis link over!!!");
	}

	private static Jedis getJedis() throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
		} catch (Exception e) {
			log.error("get jedis instance error", e);
			throw e;
		}
		return jedis;
	}

	public static void destroy() {
		pool.close();
	}

	public static void closeResource(Jedis jedis) {
		if (jedis != null) {
			try {
				jedis.close();
			} catch (Exception e) {
				log.error("close jedis error", e);
			}
			try {
				if (jedis.isConnected()) {
					jedis.disconnect();
				}
			} catch (Exception e) {
				log.error("disconnect jedis error", e);
			}
		}
	}

	public static Long delKey(String key) {
		Long ret = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			ret = jedis.del(key);
		} catch (Exception e) {
			log.info("delete redis key error ", e);
		} finally {
			closeResource(jedis);
		}
		return ret;
	}

	public static void setString(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.set(key, value);
		} catch (Exception e) {
			log.error("redis set string value error", e);
		} finally {
			closeResource(jedis);
		}
	}

	public static String getString(String key) {
		String ret = "";
		Jedis jedis = null;
		try {
			jedis = getJedis();
			ret = jedis.get(key);
		} catch (Exception e) {
			log.error("redis set string value error", e);
		} finally {
			closeResource(jedis);
		}
		return ret;
	}

	public static void delString(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			log.error("redis set string value error", e);
		} finally {
			closeResource(jedis);
		}
	}
}
