package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.LaboratorioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    // POST - Cadastrar laboratório
    @PostMapping
    public ResponseEntity<Object> cadastrarLaboratorio(
            @RequestBody @Valid LaboratorioDTO laboratorioDTO) {

        return new ResponseEntity<>(
                "Laboratório cadastrado com sucesso!",
                HttpStatus.CREATED
        );
    }

    // GET - Buscar laboratório por ID
    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioDTO> buscarLaboratorioPorId(
            @PathVariable Integer id) {

        return ResponseEntity.notFound().build();
    }

    // GET - Consultar laboratórios por nome, capacidade e localização
    @GetMapping
    public ResponseEntity<List<LaboratorioDTO>> pesquisarLaboratorios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer capacidade,
            @RequestParam(required = false) String localizacao) {

        return ResponseEntity.ok(List.of());
    }

    // PUT - Atualizar laboratório
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarLaboratorio(
            @PathVariable Integer id,
            @RequestBody @Valid LaboratorioDTO laboratorioDTO) {

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir laboratório
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirLaboratorio(
            @PathVariable Integer id) {

        return ResponseEntity.noContent().build();
    }
}