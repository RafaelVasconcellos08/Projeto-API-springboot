package com.example.SisAcademicoAlunos_19.controller;


import com.example.SisAcademicoAlunos_19.controller.dto.ReservaDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.ReservaFiltroDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    // POST - Criar reserva
    @PostMapping
    public ResponseEntity<Object> cadastrarReserva(
            @RequestBody @Valid ReservaDTO reservaDTO) {

        return new ResponseEntity<>(
                "Reserva realizada com sucesso!",
                HttpStatus.CREATED
        );
    }

    // GET - Buscar reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReservaPorId(
            @PathVariable Integer id) {

        return ResponseEntity.notFound().build();
    }

    // GET - Consultar reservas utilizando filtros
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> pesquisarReservas(
            ReservaFiltroDTO filtro) {

        return ResponseEntity.ok(List.of());
    }

    // PUT - Atualizar reserva
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarReserva(
            @PathVariable Integer id,
            @RequestBody @Valid ReservaDTO reservaDTO) {

        return ResponseEntity.ok().build();
    }

    // DELETE - Cancelar/excluir reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirReserva(
            @PathVariable Integer id) {

        return ResponseEntity.noContent().build();
    }
}