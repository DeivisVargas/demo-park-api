package com.mballer.demo_park_api.service;

import com.mballer.demo_park_api.entity.Usuario;
import com.mballer.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//para fazer a injeção de dependencia via metodo construtor
@RequiredArgsConstructor

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true) // para dizer que é apenas uma consulta
    public Usuario buscarPorId(Long id){
        //a varias maneiras de consultar , essa orElseThrow caso não encontre o usuario lança uma exeção
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
    }


}
