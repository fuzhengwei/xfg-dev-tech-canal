package cn.bugstack.xfg.dev.tech.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.elasticsearch.xpack.sql.jdbc.EsDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Configuration
    @MapperScan(basePackages = "cn.bugstack.xfg.dev.tech.infrastructure.dao.elasticsearch", sqlSessionFactoryRef = "elasticsearchSqlSessionFactory")
    static class ElasticsearchMyBatisConfig {

        @Bean("elasticsearchDataSource")
        @ConfigurationProperties(prefix = "spring.elasticsearch.datasource")
        public DataSource igniteDataSource(Environment environment) {
            return new EsDataSource();
        }

        @Bean("elasticsearchSqlSessionFactory")
        public SqlSessionFactory elasticsearchSqlSessionFactory(DataSource elasticsearchDataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(elasticsearchDataSource);
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/elasticsearch/*.xml"));
            return factoryBean.getObject();
        }
    }

    @Configuration
    @MapperScan(basePackages = "cn.bugstack.xfg.dev.tech.infrastructure.dao.mysql", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
    static class MysqlMyBatisConfig {

        @Bean("mysqlDataSource")
        @ConfigurationProperties(prefix = "spring.mysql.datasource")
        public DataSource mysqlDataSource(Environment environment) {
            return DataSourceBuilder.create()
                    .url(environment.getProperty("spring.mysql.datasource.url"))
                    .driverClassName(environment.getProperty("spring.mysql.datasource.driver-class-name"))
                    .build();
        }

        @Bean("mysqlSqlSessionFactory")
        public SqlSessionFactory mysqlSqlSessionFactory(DataSource mysqlDataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(mysqlDataSource);
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/mysql/*.xml"));
            return factoryBean.getObject();
        }
    }

}
