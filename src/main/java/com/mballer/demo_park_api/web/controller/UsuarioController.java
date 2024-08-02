package com.mballer.demo_park_api.web.controller;


import com.mballer.demo_park_api.entity.Usuario;
import com.mballer.demo_park_api.service.UsuarioService;
import com.mballer.demo_park_api.web.dto.UsuarioCreateDto;
import com.mballer.demo_park_api.web.dto.UsuarioResponseDto;
import com.mballer.demo_park_api.web.dto.UsuarioSenhaDto;
import com.mballer.demo_park_api.web.dto.mapper.UsuarioMapper;
import com.mballer.demo_park_api.web.exeption.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios" , description = "Comtém operações relativas aos recursos de cadastro, edição e leitura de um usuário")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController
{

    private final UsuarioService usuarioService;

    //documentando swagger no recurso create
    @Operation(
            summary = "Criar um novo usuário" ,description = "Recurso para criar um novo usuário",
            responses = {
                    @ApiResponse(responseCode = "201" , description = "Recurso criado com sucesso" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation =UsuarioResponseDto.class ))),
                    @ApiResponse(responseCode = "409" , description = "Usuario email já cadastrado no sistema" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class ))),
                    @ApiResponse(responseCode = "422" , description = "Recurso não processado por dados de entrada inválidos" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class )))

            }

    )
    //@Valid para dizer ao controller que ele deve validar os campos
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto){
        Usuario user =  usuarioService.salvar(UsuarioMapper.toUsuario(createDto));

        return  ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }


    @Operation(
            summary = "Consulta usuário por id" , description = "Recurso para consultar um usuário por id" ,
            responses = {
                    @ApiResponse(responseCode = "200" , description = "Consulta realizada com sucesso" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation =UsuarioResponseDto.class ))),

                    @ApiResponse(responseCode = "404" , description = "Recurso não encontrado" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class )))

            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id){
        Usuario user =  usuarioService.buscarPorId(id);
        return  ResponseEntity.ok(UsuarioMapper.toDto(user));
    }


    @Operation(
            summary = "Atualiza senha do usuário " , description = "Recurso para atualizar senha de um usuário" ,
            responses = {
                    @ApiResponse(responseCode = "204" , description = "Senha atualizada com sucesso" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = void.class ))),

                    @ApiResponse(responseCode = "404" , description = "Recurso não encontrado" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class ))),

                    @ApiResponse(responseCode = "400" , description = "Senha não respeita as regras de validação" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class )))

            }
    )
    //alteração de senha
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword( @PathVariable Long id,@Valid @RequestBody UsuarioSenhaDto dto){
        Usuario user =  usuarioService.editarSenha(id , dto.getSenhaAtual(), dto.getNovaSenha() , dto.getConfirmaSenha());
        return  ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Lista todos os usuários " , description = "Recurso para listar todos os usuários" ,
            responses = {
                    @ApiResponse(responseCode = "200" , description = "Dados carregados com sucesso" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = UsuarioResponseDto.class ))),

                    @ApiResponse(responseCode = "404" , description = "Recurso não encontrado" ,
                            content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class ))),



            }
    )
    @GetMapping()
    public ResponseEntity<List<UsuarioResponseDto>> getAll(){
        List<Usuario> users =  usuarioService.buscarTodos();
        return  ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
