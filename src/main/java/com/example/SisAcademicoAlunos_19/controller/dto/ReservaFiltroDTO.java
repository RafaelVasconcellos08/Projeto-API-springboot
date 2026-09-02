package com.example.SisAcademicoAlunos_19.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaFiltroDTO(

        String codigoRecurso,

        String nomeRecurso,

        LocalDate dataInicial,

        LocalDate dataFinal,

        LocalTime horaInicial,

        LocalTime horaFinal,

        Integer usuarioId,

        Integer statusId
) {
}