package com.example.SisAcademicoAlunos_19.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.ReservaDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.ReservaFiltroDTO;
import com.example.SisAcademicoAlunos_19.mapper.ReservaMapper;
import com.example.SisAcademicoAlunos_19.model.Reserva;
import com.example.SisAcademicoAlunos_19.service.ReservaService;
import com.example.SisAcademicoAlunos_19.service.StatusService;
import com.example.SisAcademicoAlunos_19.service.UsuarioService;
import com.example.SisAcademicoAlunos_19.service.LaboratorioService;
import com.example.SisAcademicoAlunos_19.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservas")
public class ReservaController
{
    private final ReservaService reservaService;
    private final ReservaMapper reservaMapper;
    private final UsuarioService usuarioService;
    private final LaboratorioService laboratorioService;
    private final SalaService salaService;
    private final StatusService statusService;

    public ReservaController(
            ReservaService reservaService,
            ReservaMapper reservaMapper,
            UsuarioService usuarioService,
            LaboratorioService laboratorioService,
            SalaService salaService,
            StatusService statusService)
    {
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
        this.usuarioService = usuarioService;
        this.laboratorioService = laboratorioService;
        this.salaService = salaService;
        this.statusService = statusService;
    }



    @PostMapping
    public ResponseEntity<Object> cadastrarReserva(
            @RequestBody @Valid ReservaDTO reservaDTO)
    {
        Reserva reserva = reservaMapper.paraEntidade(reservaDTO);

        var usuarioOptional =
                usuarioService.pegarDadosUsuarioPorId(
                        reservaDTO.usuarioId());

        if (usuarioOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }

        reserva.setUsuario(usuarioOptional.get());

        if (reservaDTO.laboratorioCodigo() == null &&
                reservaDTO.salaCodigo() == null)
        {
            return ResponseEntity
                    .badRequest()
                    .body("É necessário informar um laboratório ou uma sala.");
        }

        if (reservaDTO.laboratorioCodigo() != null &&
                reservaDTO.salaCodigo() != null)
        {
            return ResponseEntity
                    .badRequest()
                    .body("A reserva deve possuir apenas um recurso.");
        }

        if (reservaDTO.laboratorioCodigo() != null)
        {
            var laboratorioOptional =
                    laboratorioService.pegarDadosLaboratorioPorCodigo(
                            reservaDTO.laboratorioCodigo());

            if (laboratorioOptional.isEmpty())
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Laboratório não encontrado.");
            }

            reserva.setLaboratorio(laboratorioOptional.get());
        }

        if (reservaDTO.salaCodigo() != null)
        {
            var salaOptional =
                    salaService.pegarDadosSalaPorCodigo(
                            reservaDTO.salaCodigo());

            if (salaOptional.isEmpty())
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Sala não encontrada.");
            }

            reserva.setSala(salaOptional.get());
        }

        var statusOptional =
                statusService.pegarStatusReservaPorCodigo(
                        reservaDTO.statusCodigo());

        if (statusOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Status da reserva não encontrado.");
        }

        reserva.setStatus(statusOptional.get());

        // Salvar
        reservaService.inserirReserva(reserva);

        return new ResponseEntity<>(
                reservaMapper.paraDTO(reserva),
                HttpStatus.CREATED
        );
    }



    @GetMapping
    public ResponseEntity<List<ReservaDTO>> pesquisarReservas(
            @ModelAttribute ReservaFiltroDTO filtro)
    {
        List<Reserva> resultado =
                reservaService.pesquisarReservas(
                        filtro.codigoRecurso(),
                        filtro.nomeRecurso(),
                        filtro.dataInicial(),
                        filtro.dataFinal(),
                        filtro.horaInicial(),
                        filtro.horaFinal(),
                        filtro.usuarioId(),
                        filtro.statusCodigo()
                );

        List<ReservaDTO> lista = resultado
                .stream()
                .map(reservaMapper::paraDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReserva(
            @PathVariable Integer id)
    {
        return reservaService
                .pegarDadosReservaPorId(id)
                .map(reservaMapper::paraDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarReserva(
            @PathVariable Integer id,
            @RequestBody @Valid ReservaDTO reservaDTO)
    {
        var reservaOptional =
                reservaService.pegarDadosReservaPorId(id);

        if (reservaOptional.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        Reserva reserva =
                reservaMapper.paraEntidade(reservaDTO);

        reserva.setId(id);

        var usuarioOptional =
                usuarioService.pegarDadosUsuarioPorId(
                        reservaDTO.usuarioId());

        if (usuarioOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }

        reserva.setUsuario(usuarioOptional.get());

        if (reservaDTO.laboratorioCodigo() == null &&
                reservaDTO.salaCodigo() == null)
        {
            return ResponseEntity
                    .badRequest()
                    .body("É necessário informar um laboratório ou uma sala.");
        }

        if (reservaDTO.laboratorioCodigo() != null &&
                reservaDTO.salaCodigo() != null)
        {
            return ResponseEntity
                    .badRequest()
                    .body("A reserva deve possuir apenas um recurso.");
        }

        if (reservaDTO.laboratorioCodigo() != null)
        {
            var laboratorioOptional =
                    laboratorioService.pegarDadosLaboratorioPorCodigo(
                            reservaDTO.laboratorioCodigo());

            if (laboratorioOptional.isEmpty())
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Laboratório não encontrado.");
            }

            reserva.setLaboratorio(laboratorioOptional.get());
            reserva.setSala(null);
        }

        if (reservaDTO.salaCodigo() != null)
        {
            var salaOptional =
                    salaService.pegarDadosSalaPorCodigo(
                            reservaDTO.salaCodigo());

            if (salaOptional.isEmpty())
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Sala não encontrada.");
            }

            reserva.setSala(salaOptional.get());
            reserva.setLaboratorio(null);
        }

        var statusOptional =
                statusService.pegarStatusReservaPorCodigo(
                        reservaDTO.statusCodigo());

        if (statusOptional.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Status da reserva não encontrado.");
        }

        reserva.setStatus(statusOptional.get());

        reservaService.atualizarReserva(reserva);

        return ResponseEntity.ok(
                reservaMapper.paraDTO(reserva)
        );
    }
}