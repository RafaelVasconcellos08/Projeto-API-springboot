package com.example.SisAcademicoAlunos_19.service;

import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import com.example.SisAcademicoAlunos_19.model.Laboratorio;
import com.example.SisAcademicoAlunos_19.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService
{
    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository)
    {
        this.laboratorioRepository = laboratorioRepository;
    }

    // Cadastrar laboratório
    public void inserirLaboratorio(Laboratorio laboratorio)
    {
        if (laboratorioRepository.existsById(laboratorio.getCodigo()))
        {
            throw new RegistroDuplicadoException(
                    "Já existe um laboratório com este código.");
        }

        laboratorioRepository.save(laboratorio);
    }

    // Buscar laboratório por código
    public Optional<Laboratorio> pegarDadosLaboratorioPorCodigo(
            Integer codigo)
    {
        return laboratorioRepository.findById(codigo);
    }

    // Pesquisar laboratórios
    public List<Laboratorio> pesquisarLaboratorios(
            String nome,
            Integer capacidade,
            String localizacao,
            Integer statusCodigo)
    {
        if (nome == null &&
                capacidade == null &&
                localizacao == null &&
                statusCodigo == null)
        {
            return laboratorioRepository.findAll();
        }

        if (nome != null &&
                capacidade == null &&
                localizacao == null &&
                statusCodigo == null)
        {
            return laboratorioRepository.findByNome(nome);
        }

        if (nome == null &&
                capacidade != null &&
                localizacao == null &&
                statusCodigo == null)
        {
            return laboratorioRepository.findByCapacidade(capacidade);
        }

        if (nome == null &&
                capacidade == null &&
                localizacao != null &&
                statusCodigo == null)
        {
            return laboratorioRepository.findByLocalizacao(localizacao);
        }

        /*
         * As demais combinações serão tratadas filtrando
         * os registros encontrados.
         */
        return laboratorioRepository.findAll()
                .stream()
                .filter(l -> nome == null ||
                        l.getNome().equalsIgnoreCase(nome))
                .filter(l -> capacidade == null ||
                        l.getCapacidade().equals(capacidade))
                .filter(l -> localizacao == null ||
                        l.getLocalizacao().equalsIgnoreCase(localizacao))
                .filter(l -> statusCodigo == null ||
                        l.getStatus().getCodigo().equals(statusCodigo))
                .toList();
    }

    // Atualizar laboratório
    public void atualizarLaboratorio(Laboratorio laboratorio)
    {
        laboratorioRepository.save(laboratorio);
    }
}