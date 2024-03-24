package cn.bugstack.xfg.dev.tech.test;

import cn.bugstack.xfg.dev.tech.infrastructure.dao.mysql.IMySQLUserOrderDao;
import cn.bugstack.xfg.dev.tech.infrastructure.po.UserOrderPO;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    public static void main(String[] args) {
        System.out.println(10001L % 2);
    }

    @Resource
    private IMySQLUserOrderDao userOrderDao;

    @Test
    public void test_selectByUserId() {
        List<UserOrderPO> list = userOrderDao.selectByUserId("xfg_PrmgwQ");
        log.info("测试结果：{}", JSON.toJSONString(list));
    }

    @Test
    public void test_insert() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            UserOrderPO userOrderPO = UserOrderPO.builder()
                    .userName("小傅哥")
                    .userId("xfg_" + RandomStringUtils.randomAlphabetic(6))
                    .userMobile("+86 13521408***")
                    .sku("13811216")
                    .skuName("《手写MyBatis：渐进式源码实践》")
                    .orderId(RandomStringUtils.randomNumeric(11))
                    .quantity(1)
                    .unitPrice(BigDecimal.valueOf(128))
                    .discountAmount(BigDecimal.valueOf(50))
                    .tax(BigDecimal.ZERO)
                    .totalAmount(BigDecimal.valueOf(78))
                    .orderDate(new Date())
                    .orderStatus(0)
                    .isDelete(0)
                    .uuid(UUID.randomUUID().toString().replace("-", ""))
                    .ipv4("127.0.0.1")
                    .ipv6("2001:0db8:85a3:0000:0000:8a2e:0370:7334".getBytes())
                    .extData("{\"device\": {\"machine\": \"IPhone 14 Pro\", \"location\": \"shanghai\"}}")
                    .build();

            userOrderDao.insert(userOrderPO);

            Thread.sleep(100);
        }
    }

    /**
     * 路由测试
     */
    @Test
    public void test_idx() {
        for (int i = 0; i < 50; i++) {
            String user_id = "xfg_" + RandomStringUtils.randomAlphabetic(6);
            log.info("测试结果 {}", (user_id.hashCode() ^ (user_id.hashCode()) >>> 16) & 3);
        }
    }

    /**
     * 在 Redisson 中，当你调用 getMap 方法时，如果指定的 key 不存在，Redisson 并不会立即在 Redis 数据库中创建这个 key。相反，它会返回一个 RMap 对象的实例，这个实例是一个本地的 Java 对象，它代表了 Redis 中的一个哈希（hash）。
     * <p>
     * 当你开始使用这个 RMap 实例进行操作，比如添加键值对，那么 Redisson 会在 Redis 数据库中创建相应的 key，并将数据存储在这个 key 对应的哈希中。如果你只是获取了 RMap 实例而没有进行任何操作，那么在 Redis 数据库中是不会有任何变化的。
     * <p>
     * 简单来说，getMap 方法返回的 RMap 对象是懒加载的，只有在你实际进行操作时，Redis 数据库中的数据结构才会被创建或修改。
     */
    @Test
    public void test_query_es() throws IOException {
        String address = "jdbc:es://http://127.0.0.1:9200";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(address);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from \"xfg_dev_tech.user_order\"");
            while (results.next()) {
                System.out.println(results.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
