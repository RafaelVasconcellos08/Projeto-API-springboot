package com.example.SisAcademicoAlunos_19.mapper;

import com.example.SisAcademicoAlunos_19.controller.dto.StatusReservaDTO;
import com.example.SisAcademicoAlunos_19.model.StatusReserva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusReservaMapper
{
    StatusReservaDTO paraDTO(StatusReserva statusReserva);

    StatusReserva paraEntidade(StatusReservaDTO statusReservaDTO);
}