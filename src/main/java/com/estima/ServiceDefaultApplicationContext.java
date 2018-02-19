package com.estima;

import com.estima.config.AuthorizationServerConfig;
import com.estima.config.CorsConfig;
import com.estima.config.ResourceServerConfig;
import com.estima.config.WebSecurityConfigurer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.estima.resource")
@Import({
        // security
        WebSecurityConfigurer.class,
        AuthorizationServerConfig.class,
        ResourceServerConfig.class,

        // web
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
        LiquibaseAutoConfiguration.class
})
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceDefaultApplicationContext {

    @Bean
    @ConfigurationProperties(prefix = "oauth2-data-db")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
}