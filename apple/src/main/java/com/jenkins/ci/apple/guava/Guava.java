/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Guava
 * Author:   jason.yangpan
 * Date:     2019/9/24 10:47
 * Description: guava
 * History:
 * <author>          <time>          <version>          <desc>
 * jason.yangpan           2019/9/24           版本号            guava
 */
package com.jenkins.ci.apple.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈guava〉
 *
 * @author jason.yangpan
 * @create 2019/9/24
 * @since 1.0.0
 */
public class Guava {

    public static void main(String[] args) {
        String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 这里的1表示每秒允许处理的量为1个
        RateLimiter limiter = RateLimiter.create(1.0);
        for (int i = 1; i <= 10; i++) {
            limiter.acquire();// 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
        }
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("start time:" + start);
        System.out.println("end time:" + end);
    }

}