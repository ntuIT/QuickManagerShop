package com.qman.web;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuickManApp implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuickManApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO Auto-generated method stub
    }
}
