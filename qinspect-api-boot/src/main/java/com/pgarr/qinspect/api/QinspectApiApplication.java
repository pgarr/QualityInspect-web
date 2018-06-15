package com.pgarr.qinspect.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QinspectApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QinspectApiApplication.class, args);
    }
}
