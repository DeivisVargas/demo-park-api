package com.mballer.demo_park_api.repository;

import com.mballer.demo_park_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository; //fornece os metodos prontos para manipular banco de dados

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}