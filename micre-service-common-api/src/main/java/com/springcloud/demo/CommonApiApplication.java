package com.springcloud.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommonApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApiApplication.class, args);
    }

}
