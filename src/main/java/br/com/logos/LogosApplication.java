package br.com.logos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class LogosApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogosApplication.class, args);
    }
}
