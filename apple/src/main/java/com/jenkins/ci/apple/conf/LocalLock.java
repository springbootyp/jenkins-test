package com.jenkins.ci.apple.conf;

/**
 * @author jason.yangpan
 *
 * Message :  锁的注解
 */

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {
    String key() default "";
}
