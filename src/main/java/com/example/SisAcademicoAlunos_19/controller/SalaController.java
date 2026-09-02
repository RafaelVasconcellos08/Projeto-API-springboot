package com.example.SisAcademicoAlunos_19.controller;


import com.example.SisAcademicoAlunos_19.controller.dto.SalaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    // POST - Cadastrar sala
    @PostMapping
    public ResponseEntity<Object> cadastrarSala(
            @RequestBody @Valid SalaDTO salaDTO) {

        return new ResponseEntity<>(
                "Sala cadastrada com sucesso!",
                HttpStatus.CREATED
        );
    }

    // GET - Buscar sala por ID
    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> buscarSalaPorId(
            @PathVariable Integer id) {

        return ResponseEntity.notFound().build();
    }

    // GET - Consultar salas por nome, capacidade e localização
    @GetMapping
    public ResponseEntity<List<SalaDTO>> pesquisarSalas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer capacidade,
            @RequestParam(required = false) String localizacao) {

        return ResponseEntity.ok(List.of());
    }

    // PUT - Atualizar sala
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarSala(
            @PathVariable Integer id,
            @RequestBody @Valid SalaDTO salaDTO) {

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir sala
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirSala(
            @PathVariable Integer id) {

        return ResponseEntity.noContent().build();
    }
}
