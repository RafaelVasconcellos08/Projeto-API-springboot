package com.example.SisAcademicoAlunos_19.mapper;

import com.example.SisAcademicoAlunos_19.controller.dto.StatusRecursoDTO;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusRecursoMapper
{
    StatusRecursoDTO paraDTO(StatusRecurso statusRecurso);

    StatusRecurso paraEntidade(StatusRecursoDTO statusRecursoDTO);
}