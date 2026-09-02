package com.example.SisAcademicoAlunos_19.repository;

package com.example.SisAcademicoAlunos_19.repository;

import com.example.SisAcademicoAlunos_19.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    // Pesquisar usuário por CPF
    Optional<Usuario> findByCpf(String cpf);

    // Pesquisar usuário por e-mail
    Optional<Usuario> findByEmail(String email);

    // Pesquisar usuário por login
    Optional<Usuario> findByLogin(String login);

    // Pesquisar usuários por data de aniversário
    List<Usuario> findByDataAniversario(LocalDate dataAniversario);

    // Pesquisar usuários por e-mail e data de aniversário
    List<Usuario> findByEmailAndDataAniversario(
            String email,
            LocalDate dataAniversario);

    // Verificar se já existe usuário com determinado CPF
    boolean existsByCpf(String cpf);

    // Verificar se já existe usuário com determinado e-mail
    boolean existsByEmail(String email);

    // Verificar se já existe usuário com determinado login
    boolean existsByLogin(String login);
}