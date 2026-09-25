package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.Reserva;
import com.example.SisAcademicoAlunos_19.model.StatusReserva;
import com.example.SisAcademicoAlunos_19.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>
{
    // Reservas por usuário
    List<Reserva> findByUsuario(Usuario usuario);

    // Reservas por status
    List<Reserva> findByStatus(StatusReserva status);

    // Reservas por data
    List<Reserva> findByDataInicialAndDataFinal(
            LocalDate dataInicial,
            LocalDate dataFinal);

    // Reservas por horário
    List<Reserva> findByHoraInicialAndHoraFinal(
            LocalTime horaInicial,
            LocalTime horaFinal);

    // Reservas por laboratório
    List<Reserva> findByLaboratorioCodigo(
            Integer codigo);

    // Reservas por sala
    List<Reserva> findBySalaCodigo(
            Integer codigo);

    // Reservas de laboratório em determinada data
    List<Reserva> findByLaboratorioCodigoAndDataInicial(
            Integer codigo,
            LocalDate dataInicial);

    // Reservas de sala em determinada data
    List<Reserva> findBySalaCodigoAndDataInicial(
            Integer codigo,
            LocalDate dataInicial);
}