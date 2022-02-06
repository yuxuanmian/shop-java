package com.zlt.shop;

import com.zlt.shop.controller.Starter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

    @Autowired
    Starter starter;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);

    }



    @Override
    public void run(String... args) throws Exception {
        starter.init();
    }
}

