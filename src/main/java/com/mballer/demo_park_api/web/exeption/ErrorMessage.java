package com.mballer.demo_park_api.web.exeption;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErrorMessage {

    private String path ;
    private  String method;

    private  int status ;

    private String statusText;

    private  String message ;

    @JsonInclude(JsonInclude.Include.NON_NULL)// se o campo for null ele não irá aparecer no objeto json de resposta
    private Map<String , String> erros ;

    public ErrorMessage(){

    }

    public ErrorMessage(HttpServletRequest request , HttpStatus status , String message) {

        this.path       = request.getRequestURI();
        this.method     = request.getMethod();
        this.status     = status.value();
        this.statusText = status.getReasonPhrase() ;
        this.message    = message;

    }

    //BindingResult para acesso aos erros pquando gerados em uma validação de campos
    public ErrorMessage(HttpServletRequest request , HttpStatus status , String message , BindingResult result) {

        this.path       = request.getRequestURI();
        this.method     = request.getMethod();
        this.status     = status.value();
        this.statusText = status.getReasonPhrase() ;
        this.message    = message;

        addErrors(result);

    }

    private void addErrors(BindingResult result) {

        this.erros = new HashMap<>() ;
        for (FieldError fieldError : result.getFieldErrors()){
            this.erros.put(fieldError.getField() , fieldError.getDefaultMessage());
        }

    }

}
