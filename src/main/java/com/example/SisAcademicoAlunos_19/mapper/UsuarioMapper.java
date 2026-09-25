package com.example.SisAcademicoAlunos_19.mapper;

import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioRespostaDTO;
import com.example.SisAcademicoAlunos_19.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper
{
    UsuarioDTO paraDTO(Usuario usuario);

    Usuario paraEntidade(UsuarioDTO usuarioDTO);

    UsuarioRespostaDTO paraRespostaDTO(Usuario usuario);
}