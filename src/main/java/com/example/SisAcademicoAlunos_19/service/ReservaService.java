package com.example.SisAcademicoAlunos_19.service;

import com.example.SisAcademicoAlunos_19.exceptions.OperacaoNaoPermitidaException;
import com.example.SisAcademicoAlunos_19.model.Reserva;
import com.example.SisAcademicoAlunos_19.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService
{
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository)
    {
        this.reservaRepository = reservaRepository;
    }

    // Cadastrar reserva
    public void inserirReserva(Reserva reserva)
    {
        validarData(reserva);

        validarHorario(reserva);

        validarRecurso(reserva);

        verificarConflito(reserva);

        reservaRepository.save(reserva);
    }

    // Buscar reserva por ID
    public Optional<Reserva> pegarDadosReservaPorId(Integer id)
    {
        return reservaRepository.findById(id);
    }

    // Atualizar reserva
    public void atualizarReserva(Reserva reserva)
    {
        validarData(reserva);

        validarHorario(reserva);

        validarRecurso(reserva);

        if (reserva.getStatus() != null &&
                reserva.getStatus().getNome().equalsIgnoreCase("CANCELADA"))
        {
            validarCancelamento(reserva);
        }

        verificarConflito(reserva);

        reservaRepository.save(reserva);
    }

    // Consultar reservas
    public List<Reserva> pesquisarReservas(
            Integer codigoRecurso,
            String nomeRecurso,
            java.time.LocalDate dataInicial,
            java.time.LocalDate dataFinal,
            java.time.LocalTime horaInicial,
            java.time.LocalTime horaFinal,
            Integer usuarioId,
            Integer statusCodigo)
    {
        return reservaRepository.findAll()
                .stream()
                .filter(r -> codigoRecurso == null ||
                        possuiCodigoRecurso(r, codigoRecurso))
                .filter(r -> nomeRecurso == null ||
                        possuiNomeRecurso(r, nomeRecurso))
                .filter(r -> dataInicial == null ||
                        !r.getDataInicial().isBefore(dataInicial))
                .filter(r -> dataFinal == null ||
                        !r.getDataFinal().isAfter(dataFinal))
                .filter(r -> horaInicial == null ||
                        !r.getHoraInicial().isBefore(horaInicial))
                .filter(r -> horaFinal == null ||
                        !r.getHoraFinal().isAfter(horaFinal))
                .filter(r -> usuarioId == null ||
                        r.getUsuario().getId().equals(usuarioId))
                .filter(r -> statusCodigo == null ||
                        r.getStatus().getCodigo().equals(statusCodigo))
                .toList();
    }

    // =========================================================
    // VALIDAÇÕES
    // =========================================================

    private void validarData(Reserva reserva)
    {
        if (reserva.getDataInicial() == null ||
                reserva.getDataFinal() == null)
        {
            throw new OperacaoNaoPermitidaException(
                    "Data inicial e final são obrigatórias.");
        }

        if (reserva.getDataFinal().isBefore(
                reserva.getDataInicial()))
        {
            throw new OperacaoNaoPermitidaException(
                    "Data Final precisa ser maior ou igual à Data Inicial");
        }

        // A reserva é diária
        if (!reserva.getDataInicial().equals(
                reserva.getDataFinal()))
        {
            throw new OperacaoNaoPermitidaException(
                    "A reserva é diária/por dia");
        }
    }

    private void validarHorario(Reserva reserva)
    {
        if (reserva.getHoraInicial() == null ||
                reserva.getHoraFinal() == null)
        {
            throw new OperacaoNaoPermitidaException(
                    "Hora inicial e final são obrigatórias.");
        }

        if (!reserva.getHoraFinal().isAfter(
                reserva.getHoraInicial()))
        {
            throw new OperacaoNaoPermitidaException(
                    "Hora Final precisa ser maior que a Hora Inicial");
        }
    }

    private void validarRecurso(Reserva reserva)
    {
        if (reserva.getLaboratorio() == null &&
                reserva.getSala() == null)
        {
            throw new OperacaoNaoPermitidaException(
                    "É necessário informar um laboratório ou uma sala.");
        }

        if (reserva.getLaboratorio() != null &&
                reserva.getSala() != null)
        {
            throw new OperacaoNaoPermitidaException(
                    "A reserva deve possuir apenas um recurso.");
        }

        // BLOQUEADO não pode ser reservado
        if (reserva.getLaboratorio() != null &&
                reserva.getLaboratorio().getStatus() != null &&
                reserva.getLaboratorio().getStatus()
                        .getNome().equalsIgnoreCase("BLOQUEADO"))
        {
            throw new OperacaoNaoPermitidaException(
                    "O laboratório está bloqueado e não pode ser reservado.");
        }

        if (reserva.getSala() != null &&
                reserva.getSala().getStatus() != null &&
                reserva.getSala().getStatus()
                        .getNome().equalsIgnoreCase("BLOQUEADO"))
        {
            throw new OperacaoNaoPermitidaException(
                    "A sala está bloqueada e não pode ser reservada.");
        }
    }

    private void verificarConflito(Reserva reserva)
    {
        if (reserva.getStatus() != null &&
                reserva.getStatus().getNome().equalsIgnoreCase("CANCELADA"))
        {
            return;
        }

        List<Reserva> reservasExistentes;

        if (reserva.getLaboratorio() != null)
        {
            reservasExistentes =
                    reservaRepository
                            .findByLaboratorioCodigo(
                                    reserva.getLaboratorio().getCodigo());
        }
        else
        {
            reservasExistentes =
                    reservaRepository
                            .findBySalaCodigo(
                                    reserva.getSala().getCodigo());
        }

        for (Reserva existente : reservasExistentes)
        {
            // Não comparar a própria reserva durante o PUT
            if (reserva.getId() != null &&
                    reserva.getId().equals(existente.getId()))
            {
                continue;
            }

            if (existente.getStatus() != null &&
                    existente.getStatus().getNome()
                            .equalsIgnoreCase("CANCELADA"))
            {
                continue;
            }

            if (!existente.getDataInicial().equals(
                    reserva.getDataInicial()))
            {
                continue;
            }

            boolean conflito =
                    reserva.getHoraInicial()
                            .isBefore(existente.getHoraFinal())
                            &&
                            reserva.getHoraFinal()
                                    .isAfter(existente.getHoraInicial());

            if (conflito)
            {
                throw new OperacaoNaoPermitidaException(
                        "O recurso já possui uma reserva nesse período.");
            }
        }
    }

    private void validarCancelamento(Reserva reserva)
    {
        LocalDateTime inicioReserva =
                LocalDateTime.of(
                        reserva.getDataInicial(),
                        reserva.getHoraInicial());

        LocalDateTime agora = LocalDateTime.now();

        long horasRestantes =
                Duration.between(agora, inicioReserva).toHours();

        if (horasRestantes < 24)
        {
            throw new OperacaoNaoPermitidaException(
                    "O cancelamento só pode ser realizado com pelo menos 24 horas de antecedência.");
        }
    }

    private boolean possuiCodigoRecurso(
            Reserva reserva,
            Integer codigo)
    {
        return (reserva.getLaboratorio() != null &&
                reserva.getLaboratorio().getCodigo().equals(codigo))
                ||
                (reserva.getSala() != null &&
                        reserva.getSala().getCodigo().equals(codigo));
    }

    private boolean possuiNomeRecurso(
            Reserva reserva,
            String nome)
    {
        return (reserva.getLaboratorio() != null &&
                reserva.getLaboratorio().getNome()
                        .equalsIgnoreCase(nome))
                ||
                (reserva.getSala() != null &&
                        reserva.getSala().getNome()
                                .equalsIgnoreCase(nome));
    }
}