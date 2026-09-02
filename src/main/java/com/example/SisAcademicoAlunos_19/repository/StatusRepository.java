package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer>
{
    // Pesquisar status por código
    Optional<Status> findByCodigo(String codigo);

    // Pesquisar status por nome
    Optional<Status> findByNome(String nome);

    // Listar status por nome
    List<Status> findByNomeContaining(String nome);

    // Verificar se já existe status com determinado código
    boolean existsByCodigo(String codigo);

    // Verificar se já existe status com determinado nome
    boolean existsByNome(String nome);
}