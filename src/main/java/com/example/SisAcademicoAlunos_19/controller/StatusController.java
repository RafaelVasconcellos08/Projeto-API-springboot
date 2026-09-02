package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.StatusDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    // POST - Cadastrar status
    @PostMapping
    public ResponseEntity<Object> cadastrarStatus(
            @RequestBody @Valid StatusDTO statusDTO) {

        return new ResponseEntity<>(
                "Status cadastrado com sucesso!",
                HttpStatus.CREATED
        );
    }

    // GET - Buscar status por ID
    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> buscarStatusPorId(
            @PathVariable Integer id) {

        return ResponseEntity.notFound().build();
    }

    // GET - Listar status
    @GetMapping
    public ResponseEntity<List<StatusDTO>> listarStatus() {

        return ResponseEntity.ok(List.of());
    }

    // PUT - Atualizar status
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarStatus(
            @PathVariable Integer id,
            @RequestBody @Valid StatusDTO statusDTO) {

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir status
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirStatus(
            @PathVariable Integer id) {

        return ResponseEntity.noContent().build();
    }
}