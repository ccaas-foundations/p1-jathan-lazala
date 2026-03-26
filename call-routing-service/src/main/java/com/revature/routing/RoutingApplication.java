package com.revature.routing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class RoutingApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoutingApplication.class, args);
    }
}