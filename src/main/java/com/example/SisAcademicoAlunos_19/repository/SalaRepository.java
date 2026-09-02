package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Integer>
{
    // Pesquisar sala por nome
    List<Sala> findByNome(String nome);

    // Pesquisar sala por capacidade
    List<Sala> findByCapacidade(Integer capacidade);

    // Pesquisar sala por localização
    List<Sala> findByLocalizacao(String localizacao);

    // Pesquisar sala utilizando os três filtros
    List<Sala> findByNomeAndCapacidadeAndLocalizacao(
            String nome,
            Integer capacidade,
            String localizacao);

    // Pesquisar sala pelo código
    Optional<Sala> findByCodigo(String codigo);

    // Verificar se já existe sala com determinado código
    boolean existsByCodigo(String codigo);
}