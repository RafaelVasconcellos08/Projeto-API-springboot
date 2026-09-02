package com.example.SisAcademicoAlunos_19.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaDTO(

        Integer id,

        @NotNull(message = "Data inicial é obrigatória")
        LocalDate dataInicial,

        @NotNull(message = "Data final é obrigatória")
        LocalDate dataFinal,

        @NotNull(message = "Hora inicial é obrigatória")
        LocalTime horaInicial,

        @NotNull(message = "Hora final é obrigatória")
        LocalTime horaFinal,

        @NotNull(message = "Usuário é obrigatório")
        Integer usuarioId,

        @NotNull(message = "Recurso é obrigatório")
        Integer recursoId,

        @NotNull(message = "Status é obrigatório")
        Integer statusId
) {
}