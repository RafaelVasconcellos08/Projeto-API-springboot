package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.LoginDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // POST - Cadastro de usuário
    @PostMapping
    public ResponseEntity<Object> cadastrarUsuario(
            @RequestBody @Valid UsuarioDTO usuarioDTO) {

        // A lógica será implementada no UsuarioService

        return new ResponseEntity<>(
                "Usuário cadastrado com sucesso!",
                HttpStatus.CREATED
        );
    }

    // GET - Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(
            @PathVariable Integer id) {

        // A lógica será implementada no UsuarioService

        return ResponseEntity.notFound().build();
    }

    // GET - Consultar usuários
    // Filtros: e-mail e data de aniversário
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> pesquisarUsuarios(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String dataAniversario) {

        // A lógica será implementada no UsuarioService

        return ResponseEntity.ok(List.of());
    }

    // PUT - Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioDTO usuarioDTO) {

        // A lógica será implementada no UsuarioService

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirUsuario(
            @PathVariable Integer id) {

        // A lógica será implementada no UsuarioService

        return ResponseEntity.noContent().build();
    }

    // POST - Login
    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody @Valid LoginDTO loginDTO) {

        // A lógica será implementada no UsuarioService

        return ResponseEntity.ok().build();
    }
}