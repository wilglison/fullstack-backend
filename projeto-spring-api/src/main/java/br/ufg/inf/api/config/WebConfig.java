package br.ufg.inf.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

   @Bean
   public WebMvcConfigurer corsConfigurer() {
       return new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/api/**")
                       .allowedOrigins("http://localhost:8080", "http://wil.app.br:8080", "http://produdos.wil.app.br")
                       .allowedMethods("GET", "POST", "PUT", "DELETE")
                       .allowedHeaders("*")
                       .allowCredentials(true);
           }
       };
   }
}
