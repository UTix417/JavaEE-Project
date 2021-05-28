package com.example.demo;

import com.example.demo.controller.WebSocketController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =    SpringApplication.run(BlogApplication.class, args);;
        WebSocketController.setApplicationContext(applicationContext);

    }

}
