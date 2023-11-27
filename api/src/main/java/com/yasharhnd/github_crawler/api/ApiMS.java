package com.yasharhnd.github_crawler.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableFeignClients
@SpringBootApplication
public class ApiMS {

    public static void main(final String... args) {
        SpringApplication.run(ApiMS.class, args);
    }

}
