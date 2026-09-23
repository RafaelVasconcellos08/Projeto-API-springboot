package com.example.SisAcademicoAlunos_19.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StatusRecursoDTO(

        Integer codigo,

        @NotNull(message = "Campo obrigatório")
        @Size(min = 15, max = 20,
                message = "Quantidade de caracteres incorreta!")
        String nome
) {
}