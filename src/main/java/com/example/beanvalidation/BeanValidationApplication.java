package com.example.beanvalidation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BeanValidationApplication {

    public static void main(String[] args) {
        log.info("SpringApplication started");
        SpringApplication.run(BeanValidationApplication.class, args);
        log.info("SpringApplication finished");
    }

}
