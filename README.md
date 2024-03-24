# 通过MySQL binlog日志，使用canal同步分库分表数据，到 Elasticsearch

```java
PUT xfg_dev_tech.user_order
{
    "mappings": {
      "properties": {
        "_user_id":{"type": "text"},
        "_user_name":{"type": "text"},
        "_order_id":{"type": "text"},
        "_uuid":{"type": "text"},
        "_create_time":{"type": "date"},
        "_update_time":{"type": "date"}
      }
    }
}
```

```java
PUT /xfg_dev_tech.user_order/_mapping
{
  "properties": {
    "_sku_name": {
      "type": "text"
    }
  }
}
```

mysql -uroot -p
show master status  // binlog日志文件
reset master; // 重启日志