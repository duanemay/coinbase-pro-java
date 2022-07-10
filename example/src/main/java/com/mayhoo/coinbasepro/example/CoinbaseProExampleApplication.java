package com.mayhoo.coinbasepro.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication
@Configuration
public class CoinbaseProExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinbaseProExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner coinbaseProExampleApplicationRunner(ApplicationContext ctx) {
        return args -> {
            log.info("Application Started Successfully");
            String[] beanNames = ctx.getBeanDefinitionNames();
            for (String beanName : beanNames) {
                Object bean = ctx.getBean(beanName);
                String packageName = bean.getClass().getPackageName();
                if (packageName.startsWith("com.mayhoo.")) {
                    log.info("{}: {}.{}", beanName, packageName, bean);
                }
            }
        };
    }
}