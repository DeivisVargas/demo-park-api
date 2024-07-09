package com.mballer.demo_park_api.service;

import com.mballer.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//para fazer a injeção de dependencia via metodo construtor
@RequiredArgsConstructor

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


}
