package com.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LogProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogProviderApplication.class, args);
    }

}
