package com.example.SisAcademicoAlunos_19.mapper;

import com.example.SisAcademicoAlunos_19.controller.dto.SalaDTO;
import com.example.SisAcademicoAlunos_19.model.Sala;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalaMapper
{
    @Mapping(source = "status.codigo", target = "statusCodigo")
    SalaDTO paraDTO(Sala sala);

    @Mapping(source = "statusCodigo", target = "status")
    Sala paraEntidade(SalaDTO salaDTO);

    default StatusRecurso statusRecursoParaEntidade(Integer codigo)
    {
        if (codigo == null)
        {
            return null;
        }

        StatusRecurso status = new StatusRecurso();
        status.setCodigo(codigo);

        return status;
    }
}