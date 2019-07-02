/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestController
 * Author:   Jason
 * Date:     2019/7/2 8:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Jason              8:59
 */
package com.jenkins.ci.apple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Jason
 * @create 2019/7/2
 * @since 1.0.0
 */

@Controller
public class TestController {

    String falg = "Jenkins ....  test";

    @RequestMapping(value = "/index")
    public String index(){

        return "index";
    }
}
