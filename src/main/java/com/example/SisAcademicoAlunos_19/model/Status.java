package com.example.SisAcademicoAlunos_19.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="status")
@Data
@Getter
@Setter
@ToString
public class Status
{

    @Id
    @Column(name="codigo")
    private Integer codigo;

    @Column(name="nome", length = 30)
    private String nome;

}

