package com.mballer.demo_park_api.web.controller;


import com.mballer.demo_park_api.entity.Usuario;
import com.mballer.demo_park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor //para injeção de dependencia via metodo construtor
@RestController
@RequestMapping("api/v1/usuarios") //para denominar o mapeamento de rotas
public class UsuarioController
{

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        Usuario user =  usuarioService.salvar(usuario);

        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        Usuario user =  usuarioService.buscarPorId(id);

        return  ResponseEntity.ok(user);
    }
}
