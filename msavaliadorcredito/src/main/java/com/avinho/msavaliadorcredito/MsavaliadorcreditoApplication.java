package com.avinho.msavaliadorcredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Configuration
@EnableRabbit
public class MsavaliadorcreditoApplication implements CommandLineRunner {
    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(MsavaliadorcreditoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Success!!");
        System.out.println("Application running! => " + appName);
    }
}
