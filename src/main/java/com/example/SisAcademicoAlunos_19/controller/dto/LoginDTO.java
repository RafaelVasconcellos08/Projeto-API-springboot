package com.example.SisAcademicoAlunos_19.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

        @NotBlank(message = "Campo obrigatório")
        String login,

        @NotBlank(message = "Campo obrigatório")
        String senha
) {
}