package com.example.SisAcademicoAlunos_19.mapper;

import com.example.SisAcademicoAlunos_19.controller.dto.LaboratorioDTO;
import com.example.SisAcademicoAlunos_19.model.Laboratorio;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LaboratorioMapper
{
    @Mapping(source = "status.codigo", target = "statusCodigo")
    LaboratorioDTO paraDTO(Laboratorio laboratorio);

    @Mapping(source = "statusCodigo", target = "status")
    Laboratorio paraEntidade(LaboratorioDTO laboratorioDTO);

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