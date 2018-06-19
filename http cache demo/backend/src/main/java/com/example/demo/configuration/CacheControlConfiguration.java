package com.example.demo.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static java.util.concurrent.TimeUnit.DAYS;

@Configuration
@EnableAutoConfiguration
public class CacheControlConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final CacheControl cacheControl = CacheControl.maxAge(100, DAYS)
                .cachePrivate();

        registry.addResourceHandler("*.js")
                .setCacheControl(cacheControl)
                .addResourceLocations("classpath:/public/");

        registry.addResourceHandler("*.css")
                .setCacheControl(cacheControl)
                .addResourceLocations("classpath:/public/");
    }

}
