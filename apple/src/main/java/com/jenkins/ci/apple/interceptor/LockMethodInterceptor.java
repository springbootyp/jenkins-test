/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LockMethodInterceptor
 * Author:   jason.yangpan
 * Date:     2019/9/21 10:51
 * Description: LockMethodInterceptor
 * History:
 * <author>          <time>          <version>          <desc>
 * jason.yangpan           2019/9/21           版本号            LockMethodInterceptor
 */
package com.jenkins.ci.apple.interceptor;

/**
 * 〈一句话功能简述〉<br>
 * 〈LockMethodInterceptor 〉
 *
 * @author jason.yangpan
 * @create 2019/9/21
 * @since 1.0.0
 */

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jenkins.ci.apple.conf.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Configuration
public class LockMethodInterceptor {

    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(100)
            // 设置写缓存后 5 秒钟过期
            .expireAfterWrite(5, TimeUnit.SECONDS).build();

    @Around("execution(public * *(..)) && @annotation(com.jenkins.ci.apple.conf.LocalLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = getKey(localLock.key(), pjp.getArgs());
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                throw new RuntimeException("请勿重复请求");
            }
            // 如果是第一次请求,就将 key 当前对象压入缓存中
            CACHES.put(key, key);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        } finally {
            // TODO
        }
    }

    /**
     * key 的生成策略,如果想灵活可以写成接口与实现类的方式
     *
     * @param keyExpress 表达式
     * @param args       参数
     * @return 生成的key
     */
    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }
}