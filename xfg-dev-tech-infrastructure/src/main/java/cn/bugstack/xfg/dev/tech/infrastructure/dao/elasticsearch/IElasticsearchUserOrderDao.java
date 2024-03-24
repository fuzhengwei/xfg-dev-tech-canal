package cn.bugstack.xfg.dev.tech.infrastructure.dao.elasticsearch;

import cn.bugstack.xfg.dev.tech.infrastructure.po.UserOrderPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IElasticsearchUserOrderDao {

    List<UserOrderPO> selectByUserId();

}
