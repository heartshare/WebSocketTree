package com.bonaparte;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WebSocketApplication {

    public static void main(String[] args){
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
