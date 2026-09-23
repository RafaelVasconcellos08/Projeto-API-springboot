package com.example.SisAcademicoAlunos_19.service;

import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import com.example.SisAcademicoAlunos_19.model.StatusReserva;
import com.example.SisAcademicoAlunos_19.repository.StatusRecursoRepository;
import com.example.SisAcademicoAlunos_19.repository.StatusReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService
{
    private final StatusRecursoRepository statusRecursoRepository;
    private final StatusReservaRepository statusReservaRepository;

    public StatusService(
            StatusRecursoRepository statusRecursoRepository,
            StatusReservaRepository statusReservaRepository)
    {
        this.statusRecursoRepository = statusRecursoRepository;
        this.statusReservaRepository = statusReservaRepository;
    }

    // ============================
    // STATUS DO RECURSO
    // ============================

    public void inserirStatusRecurso(StatusRecurso status)
    {
        if (statusRecursoRepository.existsById(status.getCodigo()))
        {
            throw new RegistroDuplicadoException(
                    "Já existe um status de recurso com este código.");
        }

        statusRecursoRepository.save(status);
    }

    public List<StatusRecurso> listarStatusRecursos()
    {
        return statusRecursoRepository.findAll();
    }

    public Optional<StatusRecurso> pegarStatusRecursoPorCodigo(
            Integer codigo)
    {
        return statusRecursoRepository.findById(codigo);
    }

    public void atualizarStatusRecurso(StatusRecurso status)
    {
        statusRecursoRepository.save(status);
    }



    public void inserirStatusReserva(StatusReserva status)
    {
        if (statusReservaRepository.existsById(status.getCodigo()))
        {
            throw new RegistroDuplicadoException(
                    "Já existe um status de reserva com este código.");
        }

        statusReservaRepository.save(status);
    }

    public List<StatusReserva> listarStatusReservas()
    {
        return statusReservaRepository.findAll();
    }

    public Optional<StatusReserva> pegarStatusReservaPorCodigo(
            Integer codigo)
    {
        return statusReservaRepository.findById(codigo);
    }

    public void atualizarStatusReserva(StatusReserva status)
    {
        statusReservaRepository.save(status);
    }
}