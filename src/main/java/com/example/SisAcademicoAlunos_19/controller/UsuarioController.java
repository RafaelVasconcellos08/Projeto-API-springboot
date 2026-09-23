package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.LoginDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioDTO;
import com.example.SisAcademicoAlunos_19.model.Usuario;
import com.example.SisAcademicoAlunos_19.service.UsuarioService;
import com.example.SisAcademicoAlunos_19.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController
{
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(
            UsuarioService usuarioService,
            UsuarioMapper usuarioMapper)
    {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarUsuario(
            @RequestBody @Valid UsuarioDTO usuarioDTO)
    {
        Usuario usuario = usuarioMapper.paraEntidade(usuarioDTO);

        usuarioService.inserirUsuario(usuario);

        UsuarioDTO resposta = usuarioMapper.paraDTO(usuario);

        return new ResponseEntity<>(
                resposta,
                HttpStatus.CREATED
        );
    }



    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> pesquisarUsuarios(
            @RequestParam(value = "CPF", required = false)
            String CPF,

            @RequestParam(value = "nome", required = false)
            String nome,

            @RequestParam(value = "email", required = false)
            String email,

            @RequestParam(value = "aniversario", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate aniversario)
    {
        List<Usuario> resultado =
                usuarioService.pesquisarUsuarios(
                        CPF,
                        nome,
                        email,
                        aniversario
                );

        List<UsuarioDTO> lista = resultado
                .stream()
                .map(usuarioMapper::paraDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }



    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(
            @PathVariable Integer id)
    {
        return usuarioService.pegarDadosUsuarioPorId(id)
                .map(usuarioMapper::paraDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioDTO usuarioDTO)
    {
        var usuarioOptional =
                usuarioService.pegarDadosUsuarioPorId(id);

        if (usuarioOptional.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioMapper.paraEntidade(usuarioDTO);

        usuario.setId(id);

        usuarioService.atualizarUsuario(usuario);

        UsuarioDTO resposta = usuarioMapper.paraDTO(usuario);

        return ResponseEntity.ok(resposta);
    }




    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody @Valid LoginDTO loginDTO)
    {
        var usuarioOptional =
                usuarioService.realizarLogin(
                        loginDTO.login(),
                        loginDTO.senha()
                );

        if (usuarioOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Login ou senha inválidos.");
        }

        return ResponseEntity.ok(
                "Login realizado com sucesso!"
        );
    }
}