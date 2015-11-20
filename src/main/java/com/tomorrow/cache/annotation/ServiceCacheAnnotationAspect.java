package com.tomorrow.cache.annotation;

import java.io.Serializable;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.tomorrow.redis.service.RedisService;
import com.tomorrow.utils.ServiceCacheUtils;

@Component
@Aspect
public class ServiceCacheAnnotationAspect {

	private static Logger log = LoggerFactory.getLogger(ServiceCacheAnnotationAspect.class);

	@Resource
	RedisService redisService;

	private Gson gson = new Gson();
	private Object syncLock = new Object();

	@Around("(@within(org.springframework.stereotype.Service)||@within(org.springframework.stereotype.Repository))"
	        + "&& @annotation(com.tomorrow.cache.annotation.ServiceCache)")
	private Object cacheProcess(ProceedingJoinPoint jp) throws Throwable {
		Class<?> targetClz = jp.getTarget().getClass();
		String methodName = jp.getSignature().getName();
		if (!(jp.getSignature() instanceof MethodSignature)) {
			log.warn("该方法接口无法启用缓存功能: {}", jp.getSignature().toLongString());
			return jp.proceed();
		}

		MethodSignature methodSign = (MethodSignature) jp.getSignature();
		ServiceCache sc = ServiceCacheUtils.single().findServiceCache(targetClz, methodSign.getMethod());
		if (sc == null)
			return jp.proceed();

		int expire = sc.value() >= 0 ? sc.value() : sc.expire();
		if (expire > 0) {
			String cacheKey = ServiceCacheUtils.single().buildCacheKey(sc, targetClz, methodName, jp.getArgs());
			Object rval = null;
			if (sc.sync()) {// 判断是否进行同步操作
				synchronized (syncLock) {
					rval = cacheInvoke(sc, jp, cacheKey, expire);
				}
			} else {
				rval = cacheInvoke(sc, jp, cacheKey, expire);
			}
			return (rval instanceof Blank) ? null : rval;
		}

		return jp.proceed();
	}

	/**
	 * 方法调用
	 * 
	 * @param method
	 * @param args
	 * @param cacheKey
	 * @param expire
	 * @return
	 * @throws Throwable
	 */
	private Object cacheInvoke(ServiceCache sc, ProceedingJoinPoint jp, String cacheKey, int expire) throws Throwable {
		Class<?> returnClass = ((MethodSignature)jp.getSignature()).getReturnType();
		log.debug("Load from cache for key : {}", cacheKey);
		Object rval = redisService.get(cacheKey);
		if (rval == null) {
			log.info("Miss from cache, load backend for key : {}", cacheKey);
			rval = jp.proceed();
			rval = (rval == null && sc.nullPattern()) ? Blank.INST : rval;
			if (rval != null) {
				redisService.put(cacheKey, expire, gson.toJson(rval));
			}
		}else{
			return gson.fromJson(rval.toString(), returnClass);
		}
		return rval;
	}

	private static class Blank implements Serializable {
		private static final long serialVersionUID = 3203712628835590212L;
		private static final Blank INST = new Blank();
	}
}
