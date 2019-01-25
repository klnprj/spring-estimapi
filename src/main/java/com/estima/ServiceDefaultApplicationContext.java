package com.estima;

import com.estima.config.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.codehaus.jackson.map.util.ISO8601DateFormat;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.estima.app", "com.estima.infra", "com.estima.interfaces.rest"})
@EntityScan("com.estima.domain")
@Import({
        // security
        WebSecurityConfigurer.class,
        AuthorizationServerConfig.class,
        ResourceServerConfig.class,

        // web
        WebConfiguration.class,
        CorsConfig.class,
        WebMvcAutoConfiguration.class,
        DispatcherServletAutoConfiguration.class,
//        EmbeddedServletContainerAutoConfiguration.class,
//        ServerPropertiesAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        JacksonAutoConfiguration.class,

        // persistence
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        LiquibaseAutoConfiguration.class
})
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceDefaultApplicationContext {

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        return new Jackson2ObjectMapperBuilderCustomizer() {
//            @Override
//            public void customize(Jackson2ObjectMapperBuilder builder) {
//                builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//                builder.dateFormat(new ISO8601DateFormat());
//            }
//        };
//    }
}
