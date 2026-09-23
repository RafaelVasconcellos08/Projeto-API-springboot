package com.example.SisAcademicoAlunos_19.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reserva")
@Data
@Getter
@Setter
@ToString
public class Reserva
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_inicial", nullable = false)
    private LocalDate dataInicial;

    @Column(name = "data_final", nullable = false)
    private LocalDate dataFinal;

    @Column(name = "hora_inicial", nullable = false)
    private LocalTime horaInicial;

    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "laboratorio_codigo")
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(name = "sala_codigo")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "status_reserva_codigo", nullable = false)
    private StatusReserva status;
}