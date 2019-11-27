/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SaleService
 * Author:   jason.yangpan
 * Date:     2019/7/11 20:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jason.yangpan           2019/7/11           版本号
 */
package com.jenkins.ci.apple.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author jason.yangpan
 * @create 2019/7/11
 * @since 1.0.0
 */

@Service
public class SaleService {

//    @Autowired
//    VipDiscount vipDiscount;
//
//    @Autowired
//    NormalDisCount normalDisCount;
//
//    public double sale(String type, double fee){
//        if ("normal".equals(type)){
//                return normalDisCount.dicount(fee);
//        }else if ("vip".equals(type)){
//                return vipDiscount.dicount(fee);
//        }else {
//            return fee*1.1;
//        }
//    }




    HashMap<String, DiscountService> serviceHashMap = new HashMap<>();

    public SaleService(List<DiscountService> HashMap) {
        for (DiscountService discountService : HashMap) {
            serviceHashMap.put(discountService.type(),discountService);
        }
    }

    public double sale(String type,double fee){
        return serviceHashMap.get(type).dicount(fee);
    }
}

















