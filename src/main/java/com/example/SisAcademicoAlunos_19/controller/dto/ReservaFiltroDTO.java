package com.example.SisAcademicoAlunos_19.controller.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaFiltroDTO(

        Integer codigoRecurso,

        String nomeRecurso,

        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicial,

        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate dataFinal,

        @DateTimeFormat(pattern = "HH:mm:ss")
        LocalTime horaInicial,

        @DateTimeFormat(pattern = "HH:mm:ss")
        LocalTime horaFinal,

        Integer usuarioId,

        Integer statusCodigo
) {
}