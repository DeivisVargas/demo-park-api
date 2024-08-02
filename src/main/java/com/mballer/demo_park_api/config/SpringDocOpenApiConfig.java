package com.mballer.demo_park_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openApi(){

        return new OpenAPI()
                .info(
                        new Info()
                                .title("REST API Spring")
                                .description("Api para estudos java Spring")
                                .version("v1")
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/"))
                                .contact(new Contact().name("Deivis Vargas").email("ex@gmail.com"))
                );

    }

}
