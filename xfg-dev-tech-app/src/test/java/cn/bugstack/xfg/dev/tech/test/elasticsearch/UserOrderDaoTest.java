package cn.bugstack.xfg.dev.tech.test.elasticsearch;

import cn.bugstack.xfg.dev.tech.infrastructure.dao.elasticsearch.IElasticsearchUserOrderDao;
import cn.bugstack.xfg.dev.tech.infrastructure.po.UserOrderPO;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserOrderDaoTest {

    @Resource
    private IElasticsearchUserOrderDao userOrderDao;

    @Test
    public void test() {
        List<UserOrderPO> userOrderPOS = userOrderDao.selectByUserId();
        log.info("测试结果：{}", JSON.toJSONString(userOrderPOS));
    }

}
