package com.mballer.demo_park_api.web.dto;


import lombok.*;

//gerando metodos com lombok
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioResponseDto {

    private Long id ;
    private String username;
    private String role;

}
