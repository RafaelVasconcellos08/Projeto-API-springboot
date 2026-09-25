package com.example.SisAcademicoAlunos_19.service;

import com.example.SisAcademicoAlunos_19.exceptions.OperacaoNaoPermitidaException;
import com.example.SisAcademicoAlunos_19.exceptions.ValidacaoException;
import com.example.SisAcademicoAlunos_19.model.Reserva;
import com.example.SisAcademicoAlunos_19.repository.ReservaRepository;
import com.example.SisAcademicoAlunos_19.repository.StatusReservaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService
{
    private final ReservaRepository reservaRepository;
    private final StatusReservaRepository statusReservaRepository;

    public ReservaService(
            ReservaRepository reservaRepository,
            StatusReservaRepository statusReservaRepository)
    {
        this.reservaRepository = reservaRepository;
        this.statusReservaRepository = statusReservaRepository;
    }




    public void inserirReserva(Reserva reserva)
    {
        validarData(reserva);

        validarHorario(reserva);

        validarRecurso(reserva);

        verificarConflito(reserva);

        reservaRepository.save(reserva);
    }




    public Optional<Reserva> pegarDadosReservaPorId(Integer id)
    {
        return reservaRepository.findById(id);
    }



    public void atualizarReserva(Reserva reserva)
    {
        validarData(reserva);

        validarHorario(reserva);

        validarRecurso(reserva);

        if (reserva.getStatus() != null &&
                reserva.getStatus()
                        .getNome()
                        .equalsIgnoreCase("CANCELADA"))
        {
            validarCancelamento(reserva);
        }

        verificarConflito(reserva);

        reservaRepository.save(reserva);
    }




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

                .filter(r ->
                        codigoRecurso == null ||
                                possuiCodigoRecurso(
                                        r,
                                        codigoRecurso))

                .filter(r ->
                        nomeRecurso == null ||
                                possuiNomeRecurso(
                                        r,
                                        nomeRecurso))

                .filter(r ->
                        dataInicial == null ||
                                !r.getDataInicial()
                                        .isBefore(dataInicial))

                .filter(r ->
                        dataFinal == null ||
                                !r.getDataFinal()
                                        .isAfter(dataFinal))

                .filter(r ->
                        horaInicial == null ||
                                !r.getHoraInicial()
                                        .isBefore(horaInicial))

                .filter(r ->
                        horaFinal == null ||
                                !r.getHoraFinal()
                                        .isAfter(horaFinal))

                .filter(r ->
                        usuarioId == null ||
                                r.getUsuario()
                                        .getId()
                                        .equals(usuarioId))

                .filter(r ->
                        statusCodigo == null ||
                                r.getStatus()
                                        .getCodigo()
                                        .equals(statusCodigo))

                .toList();
    }




    private void validarData(Reserva reserva)
    {
        if (reserva.getDataInicial() == null ||
                reserva.getDataFinal() == null)
        {
            throw new ValidacaoException(
                    "Campo obrigatório"
            );
        }

        if (reserva.getDataFinal()
                .isBefore(reserva.getDataInicial()))
        {
            throw new ValidacaoException(
                    "Data Final precisa ser maior ou igual à Data Inicial"
            );
        }

        // A reserva é diária
        if (!reserva.getDataInicial()
                .equals(reserva.getDataFinal()))
        {
            throw new ValidacaoException(
                    "A reserva é diária/por dia"
            );
        }
    }



    private void validarHorario(Reserva reserva)
    {
        if (reserva.getHoraInicial() == null ||
                reserva.getHoraFinal() == null)
        {
            throw new ValidacaoException(
                    "Campo obrigatório"
            );
        }

        if (!reserva.getHoraFinal()
                .isAfter(reserva.getHoraInicial()))
        {
            throw new ValidacaoException(
                    "Hora Final precisa ser maior que a Hora Inicial"
            );
        }
    }



    private void validarRecurso(Reserva reserva)
    {
        if (reserva.getLaboratorio() == null &&
                reserva.getSala() == null)
        {
            throw new OperacaoNaoPermitidaException(
                    "É necessário informar um laboratório ou uma sala."
            );
        }

        if (reserva.getLaboratorio() != null &&
                reserva.getSala() != null)
        {
            throw new OperacaoNaoPermitidaException(
                    "A reserva deve possuir apenas um recurso."
            );
        }

        // Laboratório bloqueado
        if (reserva.getLaboratorio() != null &&
                reserva.getLaboratorio().getStatus() != null &&
                reserva.getLaboratorio()
                        .getStatus()
                        .getNome()
                        .equalsIgnoreCase("BLOQUEADO"))
        {
            throw new OperacaoNaoPermitidaException(
                    "O laboratório está bloqueado e não pode ser reservado."
            );
        }

        // Sala bloqueada
        if (reserva.getSala() != null &&
                reserva.getSala().getStatus() != null &&
                reserva.getSala()
                        .getStatus()
                        .getNome()
                        .equalsIgnoreCase("BLOQUEADO"))
        {
            throw new OperacaoNaoPermitidaException(
                    "A sala está bloqueada e não pode ser reservada."
            );
        }
    }


    // ==========================================================
    // CONFLITO DE RESERVA
    // ==========================================================

    private void verificarConflito(Reserva reserva)
    {
        List<Reserva> reservasExistentes;

        if (reserva.getLaboratorio() != null)
        {
            reservasExistentes =
                    reservaRepository
                            .findByLaboratorioCodigoAndDataInicial(
                                    reserva.getLaboratorio()
                                            .getCodigo(),
                                    reserva.getDataInicial()
                            );
        }
        else
        {
            reservasExistentes =
                    reservaRepository
                            .findBySalaCodigoAndDataInicial(
                                    reserva.getSala()
                                            .getCodigo(),
                                    reserva.getDataInicial()
                            );
        }

        for (Reserva existente : reservasExistentes)
        {
            // Ignorar a própria reserva no PUT
            if (reserva.getId() != null &&
                    reserva.getId()
                            .equals(existente.getId()))
            {
                continue;
            }

            // Reserva cancelada não ocupa o horário
            if (existente.getStatus() != null &&
                    existente.getStatus()
                            .getNome()
                            .equalsIgnoreCase("CANCELADA"))
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
                        "O recurso já possui uma reserva nesse período."
                );
            }
        }
    }


    // ==========================================================
    // CANCELAMENTO COM 24 HORAS
    // ==========================================================

    private void validarCancelamento(Reserva reserva)
    {
        LocalDateTime inicioReserva =
                LocalDateTime.of(
                        reserva.getDataInicial(),
                        reserva.getHoraInicial());

        LocalDateTime limite =
                LocalDateTime.now()
                        .plusHours(24);

        if (inicioReserva.isBefore(limite))
        {
            throw new OperacaoNaoPermitidaException(
                    "O cancelamento só pode ser realizado com pelo menos 24 horas de antecedência."
            );
        }
    }


    // ==========================================================
    // RESERVAS CONCLUÍDAS AUTOMATICAMENTE
    // Executado a cada 1 minuto
    // ==========================================================

    @Scheduled(fixedRate = 60000)
    public void atualizarReservasConcluidas()
    {
        var statusAtiva =
                statusReservaRepository
                        .findByNome("ATIVA");

        var statusConcluida =
                statusReservaRepository
                        .findByNome("CONCLUÍDA");

        if (statusAtiva.isEmpty() ||
                statusConcluida.isEmpty())
        {
            return;
        }

        List<Reserva> reservasAtivas =
                reservaRepository.findByStatus(
                        statusAtiva.get());

        LocalDateTime agora =
                LocalDateTime.now();

        for (Reserva reserva : reservasAtivas)
        {
            LocalDateTime fimReserva =
                    LocalDateTime.of(
                            reserva.getDataFinal(),
                            reserva.getHoraFinal());

            LocalDateTime momentoConclusao =
                    fimReserva.plusMinutes(1);

            if (!agora.isBefore(momentoConclusao))
            {
                reserva.setStatus(
                        statusConcluida.get()
                );

                reservaRepository.save(reserva);
            }
        }
    }


    // ==========================================================
    // VERIFICAR CÓDIGO DO RECURSO
    // ==========================================================

    private boolean possuiCodigoRecurso(
            Reserva reserva,
            Integer codigo)
    {
        return
                (reserva.getLaboratorio() != null &&
                        reserva.getLaboratorio()
                                .getCodigo()
                                .equals(codigo))
                        ||
                        (reserva.getSala() != null &&
                                reserva.getSala()
                                        .getCodigo()
                                        .equals(codigo));
    }


    // ==========================================================
    // VERIFICAR NOME DO RECURSO
    // ==========================================================

    private boolean possuiNomeRecurso(
            Reserva reserva,
            String nome)
    {
        return
                (reserva.getLaboratorio() != null &&
                        reserva.getLaboratorio()
                                .getNome()
                                .equalsIgnoreCase(nome))
                        ||
                        (reserva.getSala() != null &&
                                reserva.getSala()
                                        .getNome()
                                        .equalsIgnoreCase(nome));
    }
}