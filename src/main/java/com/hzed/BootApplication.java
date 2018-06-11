package com.hzed;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzed.easyget.infrastructure.annotation.EnableAliyun;
import com.hzed.easyget.infrastructure.annotation.EnableRedis;
import com.hzed.easyget.infrastructure.annotation.EnableRest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 启动类
 * @author guichang
 */

@Slf4j
@SpringBootApplication
@MapperScan(value = {"com.hzed.easyget.persistence.auto.mapper", "com.hzed.easyget.persistence.ext.mapper"})
@EnableAsync
@EnableTransactionManagement
@EnableScheduling
@EnableRedis
@EnableRest
@EnableAliyun
public class BootApplication {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new com.alibaba.druid.pool.DruidDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] gatewayRes = resolver.getResources("classpath*:com/hzed/easyget/persistence/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(gatewayRes);
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:/config/spring-mybatis.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 解决特殊字符
     */
    @Bean
    public ObjectMapper setObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        return objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
//        log.info("==========恭喜大佬，贺喜大佬，项目启动成功！==========");
    }
}
