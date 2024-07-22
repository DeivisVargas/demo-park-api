package com.mballer.demo_park_api.web.dto.mapper;

import com.mballer.demo_park_api.entity.Usuario;
import com.mballer.demo_park_api.web.dto.UsuarioCreateDto;
import com.mballer.demo_park_api.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto){
        return  new ModelMapper().map(createDto , Usuario.class);

    }

    //modelo de resposta
    public static UsuarioResponseDto toDto(Usuario usuario){


        //convertendo o "ROLE_ADMIN" EM APENAS "ADMIN" E SETANDO ELE NA CLASSE DTO NA sTRING role
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario , UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario , UsuarioResponseDto.class);

    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios){

        //assim a ide fala que é a forma correta
        //return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
