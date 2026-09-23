package com.example.SisAcademicoAlunos_19.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaDTO(

        Integer id,

        @NotNull(message = "Campo obrigatório")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicial,

        @NotNull(message = "Campo obrigatório")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataFinal,

        @NotNull(message = "Campo obrigatório")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime horaInicial,

        @NotNull(message = "Campo obrigatório")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime horaFinal,

        @NotNull(message = "Campo obrigatório")
        Integer usuarioId,

        Integer laboratorioCodigo,

        Integer salaCodigo,

        @NotNull(message = "Campo obrigatório")
        Integer statusCodigo
) {
}