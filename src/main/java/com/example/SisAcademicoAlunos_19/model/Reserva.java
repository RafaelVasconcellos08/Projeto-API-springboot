package com.example.SisAcademicoAlunos_19.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reserva")
@Data
@Getter
@Setter
@ToString
public class Reserva
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nome", length = 30)
    private String nome;

    @Column(name="capacidade")
    private Integer capacidade;

    @Column(name="localizacao", length = 80)
    private String localizacao;

}
