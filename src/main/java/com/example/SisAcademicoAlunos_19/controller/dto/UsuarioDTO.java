package com.example.SisAcademicoAlunos_19.controller.dto;

import com.example.SisAcademicoAlunos_19.validator.CPFValido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioDTO(

        Integer id,

        @NotNull(message = "Campo obrigatório")
        @Size(min = 11, max = 11, message = "CPF inválido")
        @CPFValido(message = "CPF inválido")
        String CPF,

        @NotNull(message = "Campo obrigatório")
        @Size(
                min = 10,
                max = 80,
                message = "Quantidade de caracteres incorreta!"
        )
        String nome,

        @NotNull(message = "Campo obrigatório")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate aniversario,

        @NotNull(message = "Campo obrigatório")
        String celular,

        @NotNull(message = "Campo obrigatório")
        @Size(
                min = 15,
                max = 80,
                message = "Quantidade de caracteres incorreta!"
        )
        @Email(message = "E-mail inválido")
        String email,

        @NotNull(message = "Campo obrigatório")
        String login,

        @NotNull(message = "Campo obrigatório")
        String senha
) {
}