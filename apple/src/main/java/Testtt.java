/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test
 * Author:   jason.yangpan
 * Date:     2019/7/11 22:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jason.yangpan           2019/7/11           版本号
 */

import com.jenkins.ci.apple.demo.SaleService;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jason.yangpan
 * @create 2019/7/11
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
public class Testtt {

    @Autowired
    private SaleService saleService;


    @Test
    public void test() {
        saleService.sale("sounct",5);
    }

}