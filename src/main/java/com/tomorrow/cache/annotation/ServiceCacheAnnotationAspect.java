/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: ServiceCacheAnnotationAspect.java
 * Author:   Administrator
 * Date:     2015年11月13日 下午3:46:46
 * Description: 
 */
package com.tomorrow.cache.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceCacheAnnotationAspect {
	private static Logger log = LoggerFactory.getLogger(ServiceCacheAnnotationAspect.class);

	// 记录方法对应的 @ServiceCache 标注对象
	private Map<String, Map<String, ServiceCache>> cacheConfig = new HashMap<String, Map<String, ServiceCache>>();

	@Around("(@within(org.springframework.stereotype.Service)||@within(org.springframework.stereotype.Repository))"
	        + "&& @annotation(com.tomorrow.cache.annotation.ServiceCache)")
	private Object cacheProcess(ProceedingJoinPoint jp) throws Throwable {
		Class<?> targetClz = jp.getTarget().getClass();
		if (!(jp.getSignature() instanceof MethodSignature)) {
			log.warn("该方法接口无法启用缓存功能: {}", jp.getSignature().toLongString());
			return jp.proceed();
		}

		MethodSignature methodSign = (MethodSignature) jp.getSignature();
		ServiceCache sc = findServiceCache(targetClz, methodSign.getMethod());
		if (sc == null)
			return jp.proceed();

		int expire = sc.value() >= 0 ? sc.value() : sc.expire();
		System.out.println("expire:"+expire);
		
		return jp.proceed();
	}

	ServiceCache findServiceCache(Class<?> targetClz, Method targetMethod) {
		Map<String, ServiceCache> classCacheConfig = cacheConfig.get(targetClz.getName());
		if (classCacheConfig == null) {
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
		return classCacheConfig.get(this.methodSignature(targetMethod.getName(), targetMethod.getParameterTypes()));
	}
	
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
