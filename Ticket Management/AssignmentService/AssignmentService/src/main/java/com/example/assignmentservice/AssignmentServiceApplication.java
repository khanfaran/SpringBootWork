package com.example.assignmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AssignmentServiceApplication {

    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}
    public static void main(String[] args) {
        SpringApplication.run(AssignmentServiceApplication.class, args);
    }

}
