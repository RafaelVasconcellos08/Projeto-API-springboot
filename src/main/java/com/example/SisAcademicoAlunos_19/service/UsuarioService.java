package com.example.SisAcademicoAlunos_19.service;

import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import com.example.SisAcademicoAlunos_19.model.Usuario;
import com.example.SisAcademicoAlunos_19.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService
{
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    // Cadastrar usuário
    public void inserirUsuario(Usuario usuario)
    {
        if (usuarioRepository.findByCPF(usuario.getCPF()).isPresent())
        {
            throw new RegistroDuplicadoException(
                    "Já existe um usuário cadastrado com este CPF.");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
        {
            throw new RegistroDuplicadoException(
                    "Já existe um usuário cadastrado com este e-mail.");
        }

        if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent())
        {
            throw new RegistroDuplicadoException(
                    "Já existe um usuário cadastrado com este login.");
        }

        usuarioRepository.save(usuario);
    }

    // Buscar usuário por ID
    public Optional<Usuario> pegarDadosUsuarioPorId(Integer id)
    {
        return usuarioRepository.findById(id);
    }

    // Pesquisar usuários
    public List<Usuario> pesquisarUsuarios(
            String CPF,
            String nome,
            String email,
            LocalDate aniversario)
    {
        if (CPF != null)
        {
            Optional<Usuario> usuario = usuarioRepository.findByCPF(CPF);

            return usuario.map(List::of).orElse(List.of());
        }

        if (nome != null)
        {
            return usuarioRepository.findByNome(nome);
        }

        if (email != null)
        {
            Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

            return usuario.map(List::of).orElse(List.of());
        }

        if (aniversario != null)
        {
            return usuarioRepository.findByAniversario(aniversario);
        }

        return usuarioRepository.findAll();
    }

    // Atualizar usuário
    public void atualizarUsuario(Usuario usuario)
    {
        usuarioRepository.save(usuario);
    }

    // Login
    public Optional<Usuario> realizarLogin(String login, String senha)
    {
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findByLogin(login);

        if (usuarioOptional.isPresent())
        {
            Usuario usuario = usuarioOptional.get();

            if (usuario.getSenha().equals(senha))
            {
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
    }
}