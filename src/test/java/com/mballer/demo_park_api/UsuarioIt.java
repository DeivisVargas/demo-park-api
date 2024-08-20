package com.mballer.demo_park_api;

import com.mballer.demo_park_api.web.dto.UsuarioCreateDto;
import com.mballer.demo_park_api.web.dto.UsuarioResponseDto;
import com.mballer.demo_park_api.web.exeption.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

//Para o tomcat usar uma porta de forma randomica
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//para importar e executa antes de ser rodado o teste para existir dados no banco de dados
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql" , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql" , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)


public class UsuarioIt {

    @Autowired
    WebTestClient testClient;

    @Test
    public  void createUsuario_ComUsernameEPAsswordValidos_RetornarUsuarioCriadoComStatus201(){
        UsuarioResponseDto responseBody = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("ronaldo@gmail.com" , "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UsuarioResponseDto.class) //fala o modelo da resposta que ele espera no teste
                .returnResult().getResponseBody();  // para que seja retornado um objeto do tipo UsuarioResponseDto


        //teste de asserção e validando se o retorno não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();

        //testando se  o Id da resposta não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();

        //teste se o username está sendo retornado corretamente
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("ronaldo@gmail.com");

        //testa se o tipo de permissão do usuário é CLIENTE
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");

    }

    @Test
    public  void createUsuario_ComUsernameInvalido_RetornarErrorMessageStatus422(){
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("" , "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class) //fala o modelo da resposta que ele espera no teste
                .returnResult().getResponseBody();  // para que seja retornado um objeto do tipo UsuarioResponseDto


        //teste de asserção e validando se o retorno não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        //Duplicando o metodo para realizar 2 tipos de testes dentro de uma funcao
        responseBody = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("Tod@" , "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class) //fala o modelo da resposta que ele espera no teste
                .returnResult().getResponseBody();  // para que seja retornado um objeto do tipo UsuarioResponseDto


        //teste de asserção e validando se o retorno não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("Tod@email" , "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class) //fala o modelo da resposta que ele espera no teste
                .returnResult().getResponseBody();  // para que seja retornado um objeto do tipo UsuarioResponseDto


        //teste de asserção e validando se o retorno não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    }

    @Test
    public  void createUsuario_ComPasswordInvalido_RetornarErrorMessageStatus422(){
        //teste de senha vazia
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("deivis@yahoo.com" , ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class) //fala o modelo da resposta que ele espera no teste
                .returnResult().getResponseBody();  // para que seja retornado um objeto do tipo UsuarioResponseDto


        //teste de asserção e validando se o retorno não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        //senha menor que 6 digitos
        responseBody = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("deivis@yahoo.com" , "12345"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class) //fala o modelo da resposta que ele espera no teste
                .returnResult().getResponseBody();  // para que seja retornado um objeto do tipo UsuarioResponseDto


        //teste de asserção e validando se o retorno não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        //senha maior que 6 digitos
        responseBody = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("deivis@yahoo.com" , "1234567"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class) //fala o modelo da resposta que ele espera no teste
                .returnResult().getResponseBody();  // para que seja retornado um objeto do tipo UsuarioResponseDto


        //teste de asserção e validando se o retorno não é nulo
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    }


}
