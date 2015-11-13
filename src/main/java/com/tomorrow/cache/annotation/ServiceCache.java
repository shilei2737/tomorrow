package com.tomorrow.cache.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD })
@Retention(RUNTIME)
public @interface ServiceCache {
	public enum Key {
		JSON, // 采用精简的JSON格式生成缓存key
		TO_STRING, // 采用 toString() 方法生成缓存key
	}

	/**
	 * 设置缓存过期时间，value变量方便设置用
	 * 
	 * @return
	 */
	int value() default -1;

	/**
	 * 设置缓存过期时间，默认 60 秒
	 * 
	 * @return
	 */
	int expire() default 60;

	/**
	 * 缓存key的生成方式，默认采用JSON方式进行系统化
	 * 
	 * @return
	 */
	Key key() default Key.JSON;

	/**
	 * 定义在参数JSON生成KEY时，只让影响缓存结果的字段进行系列化。<BR>
	 * 使用规则：<BR>
	 * 1. 返回的是数组类型，数组中的每个值与参数中的对象一一对应。不过数组的长度允许与参数个数不同，<BR>
	 * 但实际运行时将以参数长度来自动匹配数据中相应位置的值；<BR>
	 * 2. 数组中的每个值，以英文逗号（,）分隔，用来区分对象中系列化要包含的多个字段；<BR>
	 * 3. 对于参数中若有对象不需要排除字段的，请以空字符串定义；<BR>
	 * 4. 复杂DTO对象中不支持再有复杂对象作为因子Key；<BR>
	 * 
	 * 请参见 ServiceCacheAnnotationTest 测试用例的用法
	 * 
	 * @return 返回缓存排除JSON系统化KEY时的字段列表
	 */
	String[] includeKeys() default {};

	/**
	 * 参数中对象的属性个数限制，超出时系统报出运行期异常。默认允许DTO有7个属性。<BR>
	 * 该限制原因：避免缓存的接口中参数对象的滥用，把很多不是影响缓存数据的因子也一并存储到DTO中，让KEY庞大。
	 * 
	 * @return
	 */
//	int propsLenLimit() default 7;

	/**
	 * 缓存接口调用是否进行同步处理，避免缓存失效瞬间出现数据库密集请求的雪崩状况。<BR>
	 * 目前暂时不支持集群锁，只支持 JVM 级锁。<BR>
	 * 使用该参数时请了解运行机理后再用。<BR>
	 * 
	 * @return
	 */
	boolean sync() default false;
	
	/**
	 * <pre>
	 * 是否支持空对象模式，即：当接口返回空对象时，是否做NULL值的缓存。
	 * 默认是支持空对象模式。
	 * @return
	 */
	boolean nullPattern() default true;

	// 缓存的Bean对象
	// String cacheBean() default "serviceApiCache";
}
