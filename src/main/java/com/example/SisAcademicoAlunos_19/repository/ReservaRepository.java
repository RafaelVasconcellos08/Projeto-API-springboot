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
    List<Reserva> findByUsuario(Usuario usuario);

    List<Reserva> findByStatus(StatusReserva status);

    List<Reserva> findByDataInicialAndDataFinal(
            LocalDate dataInicial,
            LocalDate dataFinal);

    List<Reserva> findByHoraInicialAndHoraFinal(
            LocalTime horaInicial,
            LocalTime horaFinal);

    List<Reserva> findByLaboratorioCodigo(Integer codigo);

    List<Reserva> findBySalaCodigo(Integer codigo);
}