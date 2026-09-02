package com.example.SisAcademicoAlunos_19.model;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="usuario")
@Data
@Getter
@Setter
@ToString
public class Usuario
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="CPF", length = 11)
    private String CPF;

    @Column(name="celular", length = 11)
    private String celular;

    @Column(name="nome", length = 80)
    private String nome;

    @Column(name="email", length = 80)
    private String email;

    @Column(name="aniversario")
    LocalDate aniversario;

}

