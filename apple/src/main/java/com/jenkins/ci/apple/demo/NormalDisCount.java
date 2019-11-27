/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: NormalDisCount
 * Author:   jason.yangpan
 * Date:     2019/7/11 21:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jason.yangpan           2019/7/11           版本号
 */
package com.jenkins.ci.apple.demo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author jason.yangpan
 * @create 2019/7/11
 * @since 1.0.0
 */
public class NormalDisCount implements DiscountService{

    @Override
    public String type() {
        return "sounct";
    }

    @Override
    public double dicount(double fee) {
        return 2*fee;
    }
}



















