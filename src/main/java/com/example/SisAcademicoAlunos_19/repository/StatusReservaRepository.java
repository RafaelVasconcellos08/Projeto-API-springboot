package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusReservaRepository extends JpaRepository<StatusReserva, Integer>
{
    Optional<StatusReserva> findByNome(String nome);
}