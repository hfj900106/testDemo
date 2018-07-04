package com.hzed;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzed.easyget.infrastructure.annotation.EnableRedis;
import com.hzed.easyget.infrastructure.annotation.EnableRest;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import javax.validation.Validator;

/**
 * 启动类
 *
 * @author guichang
 */

@Slf4j
@SpringBootApplication
@MapperScan(value = {"com.hzed.easyget.persistence.auto.mapper", "com.hzed.easyget.persistence.ext.mapper"})
@EnableAsync
@EnableTransactionManagement
@EnableScheduling
@ServletComponentScan
@EnableRedis
@EnableRest
//@EnableRabbitMQ
@EnableSwagger2
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        log.info("==========恭喜大佬，贺喜大佬，项目启动成功！==========");
    }

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

    /**
     * valid校验工具
     */
    @Bean
    public Validator setValidator() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setDefaultEncoding("UTF-8");
        // 此为文件目录 messages是文件名
        rbms.setBasenames("i18n/messages");
        // 缓存时间 秒
        rbms.setCacheSeconds(2000);

        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(rbms);
        return validator;
    }

    /**
     * swagger2 初始化类
     */
    @Bean
    public Docket setDocket() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("api文档")
                .description("restfun 风格接口")
                .version("1.0")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ModuleFunc.class))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    /**
     * rabbitmq相关配置
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
