package com.avinho.eurekaserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaServer
@Configuration
public class EurekaserverApplication implements CommandLineRunner {
    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(EurekaserverApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Success!!");
        System.out.println("Application running! => " + appName);
    }

}
