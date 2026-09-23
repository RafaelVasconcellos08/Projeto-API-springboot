package com.example.SisAcademicoAlunos_19.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LaboratorioDTO(

        Integer codigo,

        @NotBlank(message = "Campo obrigatório")
        String nome,

        @NotNull(message = "Campo obrigatório")
        @Min(value = 1, message = "Valor fora do escopo")
        @Max(value = 40, message = "Valor fora do escopo")
        Integer capacidade,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 15, max = 50, message = "Quantidade de caracteres incorreta!")
        String localizacao,

        @NotNull(message = "Campo obrigatório")
        Integer statusCodigo
) {
}