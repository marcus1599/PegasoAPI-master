package com.example.Pegaso.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig  implements WebMvcConfigurer{

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns= "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // endereço separados por , na propriedade
        var allowedOrigins= corsOriginPatterns.split(",");
        // permite todas as rotas da API
        registry.addMapping("/**")
        // define quais verbos ou todos *//
        .allowedMethods("GET", "POST", "PUT")
        .allowedMethods("*")
        .allowedOrigins(allowedOrigins)
        .allowCredentials(true);}

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
        configurer.favorParameter(false)
        .ignoreAcceptHeader(false)
        .useRegisteredExtensionsOnly(false)
        .defaultContentType(MediaType.APPLICATION_JSON)
        .mediaType("json", MediaType.APPLICATION_JSON)
        .mediaType("xml", MediaType.APPLICATION_XML);
    }
    
}
