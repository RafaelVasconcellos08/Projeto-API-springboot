package com.example.SisAcademicoAlunos_19.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Data
@Getter
@Setter
@ToString
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "CPF", length = 11, nullable = false, unique = true)
    private String CPF;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name = "aniversario", nullable = false)
    private LocalDate aniversario;

    @Column(name = "celular", nullable = false)
    private String celular;

    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;
}