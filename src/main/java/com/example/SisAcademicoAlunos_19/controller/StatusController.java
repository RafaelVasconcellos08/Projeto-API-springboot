package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.StatusRecursoDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.StatusReservaDTO;
import com.example.SisAcademicoAlunos_19.mapper.StatusRecursoMapper;
import com.example.SisAcademicoAlunos_19.mapper.StatusReservaMapper;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import com.example.SisAcademicoAlunos_19.model.StatusReserva;
import com.example.SisAcademicoAlunos_19.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/status")
public class StatusController
{
    private final StatusService statusService;
    private final StatusRecursoMapper statusRecursoMapper;
    private final StatusReservaMapper statusReservaMapper;

    public StatusController(
            StatusService statusService,
            StatusRecursoMapper statusRecursoMapper,
            StatusReservaMapper statusReservaMapper)
    {
        this.statusService = statusService;
        this.statusRecursoMapper = statusRecursoMapper;
        this.statusReservaMapper = statusReservaMapper;
    }


    @PostMapping("/recursos")
    public ResponseEntity<Object> cadastrarStatusRecurso(
            @RequestBody @Valid StatusRecursoDTO statusRecursoDTO)
    {
        StatusRecurso statusRecurso =
                statusRecursoMapper.paraEntidade(statusRecursoDTO);

        statusService.inserirStatusRecurso(statusRecurso);

        return new ResponseEntity<>(
                statusRecursoMapper.paraDTO(statusRecurso),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/recursos")
    public ResponseEntity<List<StatusRecursoDTO>> listarStatusRecursos()
    {
        List<StatusRecurso> resultado =
                statusService.listarStatusRecursos();

        List<StatusRecursoDTO> lista = resultado
                .stream()
                .map(statusRecursoMapper::paraDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/recursos/{codigo}")
    public ResponseEntity<StatusRecursoDTO> buscarStatusRecurso(
            @PathVariable Integer codigo)
    {
        return statusService
                .pegarStatusRecursoPorCodigo(codigo)
                .map(statusRecursoMapper::paraDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/recursos/{codigo}")
    public ResponseEntity<Object> atualizarStatusRecurso(
            @PathVariable Integer codigo,
            @RequestBody @Valid StatusRecursoDTO statusRecursoDTO)
    {
        var statusOptional =
                statusService.pegarStatusRecursoPorCodigo(codigo);

        if (statusOptional.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        StatusRecurso statusRecurso =
                statusRecursoMapper.paraEntidade(statusRecursoDTO);

        statusRecurso.setCodigo(codigo);

        statusService.atualizarStatusRecurso(statusRecurso);

        return ResponseEntity.ok(
                statusRecursoMapper.paraDTO(statusRecurso)
        );
    }



    @PostMapping("/reservas")
    public ResponseEntity<Object> cadastrarStatusReserva(
            @RequestBody @Valid StatusReservaDTO statusReservaDTO)
    {
        StatusReserva statusReserva =
                statusReservaMapper.paraEntidade(statusReservaDTO);

        statusService.inserirStatusReserva(statusReserva);

        return new ResponseEntity<>(
                statusReservaMapper.paraDTO(statusReserva),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/reservas")
    public ResponseEntity<List<StatusReservaDTO>> listarStatusReservas()
    {
        List<StatusReserva> resultado =
                statusService.listarStatusReservas();

        List<StatusReservaDTO> lista = resultado
                .stream()
                .map(statusReservaMapper::paraDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/reservas/{codigo}")
    public ResponseEntity<StatusReservaDTO> buscarStatusReserva(
            @PathVariable Integer codigo)
    {
        return statusService
                .pegarStatusReservaPorCodigo(codigo)
                .map(statusReservaMapper::paraDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/reservas/{codigo}")
    public ResponseEntity<Object> atualizarStatusReserva(
            @PathVariable Integer codigo,
            @RequestBody @Valid StatusReservaDTO statusReservaDTO)
    {
        var statusOptional =
                statusService.pegarStatusReservaPorCodigo(codigo);

        if (statusOptional.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        StatusReserva statusReserva =
                statusReservaMapper.paraEntidade(statusReservaDTO);

        statusReserva.setCodigo(codigo);

        statusService.atualizarStatusReserva(statusReserva);

        return ResponseEntity.ok(
                statusReservaMapper.paraDTO(statusReserva)
        );
    }
}