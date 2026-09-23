package com.example.SisAcademicoAlunos_19.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status_reserva")
@Data
@Getter
@Setter
@ToString
public class StatusReserva
{
    @Id
    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "nome", length = 20, nullable = false)
    private String nome;
}