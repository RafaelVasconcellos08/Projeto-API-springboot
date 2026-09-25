package com.example.SisAcademicoAlunos_19.controller.dto;

import java.time.LocalDate;

public record UsuarioRespostaDTO(

        Integer id,

        String CPF,

        String nome,

        LocalDate aniversario,

        String celular,

        String email,

        String login
) {
}