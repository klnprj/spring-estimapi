package com.estima;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

//@SpringBootApplication
// todo: Одинаковая конфигурация в тествовом и дефолтном профиле, без автоконфигурации
@Import({ServiceDefaultApplicationContext.class})
public class ServiceLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ServiceLauncher.class, args);
    }
}
