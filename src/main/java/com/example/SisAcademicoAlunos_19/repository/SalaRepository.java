package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.Sala;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Integer>
{
    List<Sala> findByNome(String nome);

    List<Sala> findByCapacidade(Integer capacidade);

    List<Sala> findByLocalizacao(String localizacao);

    List<Sala> findByStatus(StatusRecurso status);

    List<Sala> findByNomeAndCapacidadeAndLocalizacaoAndStatus(
            String nome,
            Integer capacidade,
            String localizacao,
            StatusRecurso status);
}