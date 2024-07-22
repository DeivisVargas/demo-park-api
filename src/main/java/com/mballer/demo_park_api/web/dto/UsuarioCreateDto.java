package com.mballer.demo_park_api.web.dto;


//validação do spring para dados recebidos
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


//gerando metodos com lombok
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreateDto {

    @NotBlank // nao pode ser nula ou vazia
    @Email(message = "Formato do email inválido")
    private String username ;


    @NotBlank
    @Size(min = 6 , max = 6 , message = "A senha deve conter no mínimo 6 caracteres e no máximo 6 caracteres")
    private  String password ;
}
