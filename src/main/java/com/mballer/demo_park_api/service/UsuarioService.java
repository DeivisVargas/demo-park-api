package com.mballer.demo_park_api.service;

import com.mballer.demo_park_api.entity.Usuario;
import com.mballer.demo_park_api.exception.EntityNotFoundException;
import com.mballer.demo_park_api.exception.PasswordInvalidException;
import com.mballer.demo_park_api.exception.UsernameUniqueViolationException;
import com.mballer.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//para fazer a injeção de dependencia via metodo construtor
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {

        try {
            return usuarioRepository.save(usuario);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){ //tratando exeção do banco de dados
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado " , usuario.getUsername()));

            //String.format para formatar string com variáveis
        }

    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário {id=%s} não encontrado" , id) )
        );
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }


    //edita senha após os testes
    @Transactional
    public Usuario editarSenha(Long id , String senhaAtual, String novaSenha, String confirmaSenha){
        if(!novaSenha.equals(confirmaSenha)){
            throw new PasswordInvalidException(String.format("Nova senha {senha:%s} não confere com a confirmação de senha" , novaSenha));
        }

        Usuario user = buscarPorId(id) ;

        //verifica senha antiga
        if(!user.getPassword().equals(senhaAtual)){
            throw new PasswordInvalidException(String.format("Senha digitada {%s} não confere " , senhaAtual));

        }

        user.setPassword(novaSenha);
        return  user;
    }
}
