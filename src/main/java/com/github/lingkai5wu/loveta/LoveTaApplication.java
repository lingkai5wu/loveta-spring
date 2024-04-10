package com.github.lingkai5wu.loveta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LoveTaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveTaApplication.class, args);
    }
}