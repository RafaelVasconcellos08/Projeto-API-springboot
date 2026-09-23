package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.Laboratorio;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer>
{
    List<Laboratorio> findByNome(String nome);

    List<Laboratorio> findByCapacidade(Integer capacidade);

    List<Laboratorio> findByLocalizacao(String localizacao);

    List<Laboratorio> findByStatus(StatusRecurso status);

    List<Laboratorio> findByNomeAndCapacidadeAndLocalizacaoAndStatus(
            String nome,
            Integer capacidade,
            String localizacao,
            StatusRecurso status);
}