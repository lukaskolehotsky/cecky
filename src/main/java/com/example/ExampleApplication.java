package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
//@EnableCaching //enables Spring Caching functionality
public class ExampleApplication extends SpringBootServletInitializer {

    @Autowired
    private com.example.config.ServerProperties serverProperties;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/WEB-INF/images/")
                .setCachePeriod(0);
    }

}
