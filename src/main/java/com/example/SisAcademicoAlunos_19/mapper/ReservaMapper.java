package com.example.SisAcademicoAlunos_19.mapper;

import com.example.SisAcademicoAlunos_19.controller.dto.ReservaDTO;
import com.example.SisAcademicoAlunos_19.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper
{
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "laboratorio.codigo", target = "laboratorioCodigo")
    @Mapping(source = "sala.codigo", target = "salaCodigo")
    @Mapping(source = "status.codigo", target = "statusCodigo")
    ReservaDTO paraDTO(Reserva reserva);

    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(source = "laboratorioCodigo", target = "laboratorio")
    @Mapping(source = "salaCodigo", target = "sala")
    @Mapping(source = "statusCodigo", target = "status")
    Reserva paraEntidade(ReservaDTO reservaDTO);

    default Usuario usuarioParaEntidade(Integer id)
    {
        if (id == null)
        {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(id);

        return usuario;
    }

    default Laboratorio laboratorioParaEntidade(Integer codigo)
    {
        if (codigo == null)
        {
            return null;
        }

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setCodigo(codigo);

        return laboratorio;
    }

    default Sala salaParaEntidade(Integer codigo)
    {
        if (codigo == null)
        {
            return null;
        }

        Sala sala = new Sala();
        sala.setCodigo(codigo);

        return sala;
    }

    default StatusReserva statusReservaParaEntidade(Integer codigo)
    {
        if (codigo == null)
        {
            return null;
        }

        StatusReserva status = new StatusReserva();
        status.setCodigo(codigo);

        return status;
    }
}