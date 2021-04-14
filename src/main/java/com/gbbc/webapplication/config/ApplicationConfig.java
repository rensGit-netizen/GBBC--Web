package com.gbbc.webapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "com.gbbc.webapplication")
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        System.out.println("jspViewResolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        // maak een /webapp directory aan
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**", "images/**", "js/**")
                .addResourceLocations("classpath:/css/", "classpath:/images/", "classpath:/js/");
    }
}
