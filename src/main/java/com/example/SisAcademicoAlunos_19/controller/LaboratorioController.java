package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.LaboratorioDTO;
import com.example.SisAcademicoAlunos_19.model.Laboratorio;
import com.example.SisAcademicoAlunos_19.service.LaboratorioService;
import com.example.SisAcademicoAlunos_19.service.StatusService;
import com.example.SisAcademicoAlunos_19.mapper.LaboratorioMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController
{
    private final LaboratorioService laboratorioService;
    private final LaboratorioMapper laboratorioMapper;
    private final StatusService statusService;

    public LaboratorioController(
            LaboratorioService laboratorioService,
            LaboratorioMapper laboratorioMapper,
            StatusService statusService)
    {
        this.laboratorioService = laboratorioService;
        this.laboratorioMapper = laboratorioMapper;
        this.statusService = statusService;
    }


    @PostMapping
    public ResponseEntity<Object> cadastrarLaboratorio(
            @RequestBody @Valid LaboratorioDTO laboratorioDTO)
    {
        Laboratorio laboratorio =
                laboratorioMapper.paraEntidade(laboratorioDTO);

        var statusOptional =
                statusService.pegarStatusRecursoPorCodigo(
                        laboratorioDTO.statusCodigo());

        if (statusOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Status do recurso não encontrado.");
        }

        laboratorio.setStatus(statusOptional.get());

        laboratorioService.inserirLaboratorio(laboratorio);

        return new ResponseEntity<>(
                laboratorioMapper.paraDTO(laboratorio),
                HttpStatus.CREATED
        );
    }



    @GetMapping
    public ResponseEntity<List<LaboratorioDTO>> pesquisarLaboratorios(
            @RequestParam(value = "nome", required = false)
            String nome,

            @RequestParam(value = "capacidade", required = false)
            Integer capacidade,

            @RequestParam(value = "localizacao", required = false)
            String localizacao,

            @RequestParam(value = "statusCodigo", required = false)
            Integer statusCodigo)
    {
        List<Laboratorio> resultado =
                laboratorioService.pesquisarLaboratorios(
                        nome,
                        capacidade,
                        localizacao,
                        statusCodigo
                );

        List<LaboratorioDTO> lista = resultado
                .stream()
                .map(laboratorioMapper::paraDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }



    @GetMapping("/{codigo}")
    public ResponseEntity<LaboratorioDTO> buscarLaboratorioPorCodigo(
            @PathVariable Integer codigo)
    {
        return laboratorioService
                .pegarDadosLaboratorioPorCodigo(codigo)
                .map(laboratorioMapper::paraDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{codigo}")
    public ResponseEntity<Object> atualizarLaboratorio(
            @PathVariable Integer codigo,
            @RequestBody @Valid LaboratorioDTO laboratorioDTO)
    {
        var laboratorioOptional =
                laboratorioService.pegarDadosLaboratorioPorCodigo(codigo);

        if (laboratorioOptional.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        var statusOptional =
                statusService.pegarStatusRecursoPorCodigo(
                        laboratorioDTO.statusCodigo());

        if (statusOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Status do recurso não encontrado.");
        }

        Laboratorio laboratorio =
                laboratorioMapper.paraEntidade(laboratorioDTO);

        laboratorio.setCodigo(codigo);
        laboratorio.setStatus(statusOptional.get());

        laboratorioService.atualizarLaboratorio(laboratorio);

        return ResponseEntity.ok(
                laboratorioMapper.paraDTO(laboratorio)
        );
    }
}