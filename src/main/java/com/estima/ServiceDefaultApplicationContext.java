package com.estima;

import com.estima.config.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

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
        EmbeddedServletContainerAutoConfiguration.class,
        ServerPropertiesAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,

        // persistence
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        LiquibaseAutoConfiguration.class
})
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceDefaultApplicationContext {

    @Bean
    @ConfigurationProperties(prefix = "oauth2-data-db")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
