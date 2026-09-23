package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    Optional<Usuario> findByCPF(String CPF);

    List<Usuario> findByNome(String nome);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByAniversario(LocalDate aniversario);

    Optional<Usuario> findByLogin(String login);

}