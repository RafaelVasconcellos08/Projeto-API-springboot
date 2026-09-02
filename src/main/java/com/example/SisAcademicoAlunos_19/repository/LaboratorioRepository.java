package com.example.SisAcademicoAlunos_19.repository;


import com.example.SisAcademicoAlunos_19.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer>
{
    // Pesquisar laboratório por nome
    List<Laboratorio> findByNome(String nome);

    // Pesquisar laboratório por capacidade
    List<Laboratorio> findByCapacidade(Integer capacidade);

    // Pesquisar laboratório por localização
    List<Laboratorio> findByLocalizacao(String localizacao);

    // Pesquisar laboratório utilizando os três filtros
    List<Laboratorio> findByNomeAndCapacidadeAndLocalizacao(
            String nome,
            Integer capacidade,
            String localizacao);

    // Pesquisar laboratório pelo código
    Optional<Laboratorio> findByCodigo(String codigo);

    // Verificar se já existe laboratório com determinado código
    boolean existsByCodigo(String codigo);
}