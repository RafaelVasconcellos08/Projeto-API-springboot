package com.example.SisAcademicoAlunos_19.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="laboratorio")
@Data
@Getter
@Setter
@ToString
public class Laboratorio
{

    @Id
    @Column(name="codigo")
    private Integer codigo;

    @Column(name="nome", length = 30)
    private String nome;

    @Column(name="capacidade")
    private Integer capacidade;

    @Column(name="localizacao", length = 80)
    private String localizacao;

}


