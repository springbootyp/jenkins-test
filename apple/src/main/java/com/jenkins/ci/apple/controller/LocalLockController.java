/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LocalLockController
 * Author:   jason.yangpan
 * Date:     2019/9/21 11:01
 * Description: LocalLockController
 * History:
 * <author>          <time>          <version>          <desc>
 * jason.yangpan           2019/9/21           版本号            LocalLockController
 */
package com.jenkins.ci.apple.controller;

import com.jenkins.ci.apple.conf.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈LocalLockController〉
 *
 * @author jason.yangpan
 * @create 2019/9/21
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class LocalLockController {

    @LocalLock(key = "key:arg[0]")
    @GetMapping
    public String query(@RequestParam String token) {
        return "success - " + token;
    }

}