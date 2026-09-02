package com.example.SisAcademicoAlunos_19.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioDTO(

        Integer id,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Nome completo é obrigatório")
        @Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
        String nomeCompleto,

        @NotNull(message = "Data de aniversário é obrigatória")
        @Past(message = "Data de aniversário deve ser uma data passada")
        LocalDate dataAniversario,

        @NotBlank(message = "Celular é obrigatório")
        String celular,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Login é obrigatório")
        @Size(min = 3, max = 50, message = "Login deve ter entre 3 e 50 caracteres")
        String login,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
        String senha
) {
}