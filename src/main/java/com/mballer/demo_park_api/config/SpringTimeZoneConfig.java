package com.mballer.demo_park_api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class SpringTimeZoneConfig {

    //Faz com que apos a classe ser inicializada o metodo construtor é executado.
    @PostConstruct
     public void timeZoneConfig(){
         TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_paulo"));
     }
}
