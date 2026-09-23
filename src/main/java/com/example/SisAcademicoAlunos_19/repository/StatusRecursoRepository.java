package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRecursoRepository extends JpaRepository<StatusRecurso, Integer>
{
    Optional<StatusRecurso> findByNome(String nome);
}