package com.person.person_service.config;

import com.person.person_service.interceptor.UriMappingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UriMappingInterceptor uriMappingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Adiciona o UriMappingInterceptor para todas as requisições
        registry.addInterceptor(uriMappingInterceptor);
    }

    
}
