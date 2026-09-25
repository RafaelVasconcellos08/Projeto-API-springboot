package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.LoginDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioRespostaDTO;
import com.example.SisAcademicoAlunos_19.mapper.UsuarioMapper;
import com.example.SisAcademicoAlunos_19.model.Usuario;
import com.example.SisAcademicoAlunos_19.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    // ==========================================================
    // POST - CADASTRAR USUÁRIO
    // ==========================================================

    @PostMapping
    public ResponseEntity<Object> cadastrarUsuario(
            @RequestBody @Valid UsuarioDTO usuarioDTO)
    {
        Usuario usuario =
                usuarioMapper.paraEntidade(usuarioDTO);

        usuarioService.inserirUsuario(usuario);

        UsuarioRespostaDTO resposta =
                usuarioMapper.paraRespostaDTO(usuario);

        return new ResponseEntity<>(
                resposta,
                HttpStatus.CREATED
        );
    }


    // ==========================================================
    // GET - PESQUISAR USUÁRIOS
    // CPF, nome, e-mail e aniversário
    // ==========================================================

    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> pesquisarUsuarios(
            @RequestParam(value = "CPF", required = false)
            String CPF,

            @RequestParam(value = "nome", required = false)
            String nome,

            @RequestParam(value = "email", required = false)
            String email,

            @RequestParam(value = "aniversario", required = false)
            String aniversario)
    {
        LocalDate dataAniversario = null;

        if (aniversario != null)
        {
            dataAniversario =
                    LocalDate.parse(
                            aniversario,
                            java.time.format.DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy"
                            )
                    );
        }

        List<Usuario> resultado =
                usuarioService.pesquisarUsuarios(
                        CPF,
                        nome,
                        email,
                        dataAniversario
                );

        List<UsuarioRespostaDTO> lista = resultado
                .stream()
                .map(usuarioMapper::paraRespostaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }


    // ==========================================================
    // GET - BUSCAR USUÁRIO POR ID
    // ==========================================================

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespostaDTO> buscarUsuarioPorId(
            @PathVariable Integer id)
    {
        return usuarioService
                .pegarDadosUsuarioPorId(id)
                .map(usuarioMapper::paraRespostaDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }


    // ==========================================================
    // PUT - ATUALIZAR USUÁRIO
    // ==========================================================

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

        Usuario usuario =
                usuarioMapper.paraEntidade(usuarioDTO);

        usuario.setId(id);

        usuarioService.atualizarUsuario(usuario);

        return ResponseEntity.ok(
                usuarioMapper.paraRespostaDTO(usuario)
        );
    }


    // ==========================================================
    // POST - LOGIN
    // ==========================================================

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