package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.SalaDTO;
import com.example.SisAcademicoAlunos_19.mapper.SalaMapper;
import com.example.SisAcademicoAlunos_19.model.Sala;
import com.example.SisAcademicoAlunos_19.service.SalaService;
import com.example.SisAcademicoAlunos_19.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/salas")
public class SalaController
{
    private final SalaService salaService;
    private final SalaMapper salaMapper;
    private final StatusService statusService;

    public SalaController(
            SalaService salaService,
            SalaMapper salaMapper,
            StatusService statusService)
    {
        this.salaService = salaService;
        this.salaMapper = salaMapper;
        this.statusService = statusService;
    }



    @PostMapping
    public ResponseEntity<Object> cadastrarSala(
            @RequestBody @Valid SalaDTO salaDTO)
    {
        Sala sala = salaMapper.paraEntidade(salaDTO);

        var statusOptional =
                statusService.pegarStatusRecursoPorCodigo(
                        salaDTO.statusCodigo());

        if (statusOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Status do recurso não encontrado.");
        }

        sala.setStatus(statusOptional.get());

        salaService.inserirSala(sala);

        return new ResponseEntity<>(
                salaMapper.paraDTO(sala),
                HttpStatus.CREATED
        );
    }


    @GetMapping
    public ResponseEntity<List<SalaDTO>> pesquisarSalas(
            @RequestParam(value = "nome", required = false)
            String nome,

            @RequestParam(value = "capacidade", required = false)
            Integer capacidade,

            @RequestParam(value = "localizacao", required = false)
            String localizacao,

            @RequestParam(value = "statusCodigo", required = false)
            Integer statusCodigo)
    {
        List<Sala> resultado =
                salaService.pesquisarSalas(
                        nome,
                        capacidade,
                        localizacao,
                        statusCodigo
                );

        List<SalaDTO> lista = resultado
                .stream()
                .map(salaMapper::paraDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{codigo}")
    public ResponseEntity<SalaDTO> buscarSalaPorCodigo(
            @PathVariable Integer codigo)
    {
        return salaService
                .pegarDadosSalaPorCodigo(codigo)
                .map(salaMapper::paraDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{codigo}")
    public ResponseEntity<Object> atualizarSala(
            @PathVariable Integer codigo,
            @RequestBody @Valid SalaDTO salaDTO)
    {
        var salaOptional =
                salaService.pegarDadosSalaPorCodigo(codigo);

        if (salaOptional.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        var statusOptional =
                statusService.pegarStatusRecursoPorCodigo(
                        salaDTO.statusCodigo());

        if (statusOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Status do recurso não encontrado.");
        }

        Sala sala = salaMapper.paraEntidade(salaDTO);

        sala.setCodigo(codigo);
        sala.setStatus(statusOptional.get());

        salaService.atualizarSala(sala);

        return ResponseEntity.ok(
                salaMapper.paraDTO(sala)
        );
    }
}