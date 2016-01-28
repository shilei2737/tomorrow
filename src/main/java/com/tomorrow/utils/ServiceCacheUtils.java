package com.tomorrow.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tomorrow.cache.annotation.ServiceCache;

public class ServiceCacheUtils {
	private static Logger log = LoggerFactory.getLogger(ServiceCacheUtils.class);
	private static final String POINT = ".";
	private static final String COMMA = ",";
	private static volatile ServiceCacheUtils instance;

	public static ServiceCacheUtils single() {
		if (instance == null) {
			synchronized (ServiceCacheUtils.class) {
				if (instance == null) {
					instance = new ServiceCacheUtils();
				}
			}
		}
		return instance;
	}

	// 记录方法对应的 @ServiceCache 标注对象
	private Map<String, Map<String, ServiceCache>> cacheConfig = new HashMap<String, Map<String, ServiceCache>>();
	// 同步对象
	private Object scLock = new Object();

	/**
	 * 
	 * @param methodKey
	 *            类加方法名的唯一标识
	 * @param args
	 * @return
	 */
	public String buildCacheKey(ServiceCache sc, Class<?> targetClz, String methodName, Object[] args) {
		String argsKey = "";
		if (args != null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				switch (sc.key()) {
				case JSON:
					Object arg = args[i];
					String[] includes = sc.includeKeys();
					// 对于 args 有参数的方法，同时又是JSON生成键值的，必须定义包含的key值因子
					if (includes.length < 1)
						throw new IllegalArgumentException("请定义 includeKeys 的缓存属性");
					if (arg == null) {
						sb.append("null");
					} else {
						// 指明要系列化的参数变量，如果没指明则作为空串（""）处理
						if (i < includes.length && StringUtils.isNotBlank(includes[i])) {
							if (isBassClassType(arg.getClass())) {
								sb.append(includes[i]).append("=").append(arg);
							} else {
								Set<String> includeFields = new HashSet<String>(Arrays.asList(StringUtils.split(
								        includes[i], ", ")));
								// 目前排除复杂对象的方式，对于 Map 或 Collection
								// 等数据类型有可能会存在问题？
								Field[] argFields = arg.getClass().getDeclaredFields();
								for (Field field : argFields) {
									if (!isBassClassType(field.getType()))
										includeFields.remove(field.getName());
								}
								sb.append(arg);
							}
						}
					}
					break;
				case TO_STRING:
					sb.append(String.valueOf(args[i]).replaceAll(",", "\\\\,"));
					break;
				}
				if (i < args.length - 1)
					sb.append(COMMA);
			}
			argsKey = StringUtils.removeEnd(sb.toString(), COMMA);
		}
		String wholeKey = StringUtils.join(new String[] { targetClz.getName(), methodName, argsKey }, POINT);
		if (log.isDebugEnabled())
			log.debug("ServiceCache for key : {}", wholeKey);
//		String sigKey = StringUtils.join(
//		        new String[] { targetClz.getSimpleName(), methodName }, POINT);
		return wholeKey;
	}

	private static boolean isBassClassType(Class<?> type) {
		return ClassUtils.isPrimitiveOrWrapper(type) || type == String.class;
	}

	/**
	 * 查找方法中 @ServiceCache 标注对象
	 * @param targetClz 目标类
	 * @param targetMethod 目标方法
	 * @return
	 */
	public ServiceCache findServiceCache(Class<?> targetClz, Method targetMethod) {
		Map<String, ServiceCache> classCacheConfig = cacheConfig.get(targetClz.getName());
		if (classCacheConfig == null) {
			synchronized (scLock) {
				classCacheConfig = cacheConfig.get(targetClz.getName());
				if (classCacheConfig == null) {
					classCacheConfig = new HashMap<String, ServiceCache>();
					Method[] methods = targetClz.getMethods();
					for (Method method : methods) {
						ServiceCache sc = method.getAnnotation(ServiceCache.class);
						if (sc != null) {
							classCacheConfig.put(this.methodSignature(method.getName(), method.getParameterTypes()), sc);
						}
					}
					cacheConfig.put(targetClz.getName(), classCacheConfig);
				}
			}
		}
		return classCacheConfig.get(this.methodSignature(targetMethod.getName(), targetMethod.getParameterTypes()));
	}
	
	/*
	 * 对当前类的方法进行字符串签名处理。<pre>
	 * 格式如下：getUser(java.lang.Integer,java.lang.String[])
	 */
	private String methodSignature(String method, Object[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append(method);
		sb.append("(");
		if(args != null && args.length > 0){
			for (int size = args.length, i = 0; i < size; i++) {
				appendType(sb, (args[i] instanceof Class)? (Class<?>)args[i] : args[i].getClass());
				if (i < size - 1) {
					sb.append(",");
				}
			}
		}
		sb.append(")");
		return sb.toString();
	}

	private void appendType(StringBuilder sb, Class<?> type) {
		if (type.isArray()) {
			appendType(sb, type.getComponentType());
			sb.append("[]");
		}
		else {
			sb.append(type.getName());
		}
	}
}
