package cn.bugstack.xfg.dev.tech.infrastructure.dao.mysql;

import cn.bugstack.xfg.dev.tech.infrastructure.po.UserOrderPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMySQLUserOrderDao {

    void insert(UserOrderPO userOrderPO);

    void updateOrderStatusByUserId(String userId);

    List<UserOrderPO> selectByUserId(String userId);

}
