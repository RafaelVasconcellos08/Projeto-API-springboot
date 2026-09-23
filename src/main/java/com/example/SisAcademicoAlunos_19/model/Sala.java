package com.example.SisAcademicoAlunos_19.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sala")
@Data
@Getter
@Setter
@ToString
public class Sala
{
    @Id
    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "capacidade", nullable = false)
    private Integer capacidade;

    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "status_codigo", nullable = false)
    private StatusRecurso status;
}