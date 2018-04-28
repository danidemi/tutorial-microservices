package com.danidemi.tutorial.microservices.springboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class RestApp {

    public static void main(String[] args) {
        SpringApplication.run(RestApp.class, args);
    }

}
