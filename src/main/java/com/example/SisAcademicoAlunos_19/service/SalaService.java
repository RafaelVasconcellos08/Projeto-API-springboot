package com.example.SisAcademicoAlunos_19.service;

import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import com.example.SisAcademicoAlunos_19.model.Sala;
import com.example.SisAcademicoAlunos_19.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService
{
    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository)
    {
        this.salaRepository = salaRepository;
    }

    // Cadastrar sala
    public void inserirSala(Sala sala)
    {
        if (salaRepository.existsById(sala.getCodigo()))
        {
            throw new RegistroDuplicadoException(
                    "Já existe uma sala com este código.");
        }

        salaRepository.save(sala);
    }

    // Buscar sala por código
    public Optional<Sala> pegarDadosSalaPorCodigo(Integer codigo)
    {
        return salaRepository.findById(codigo);
    }

    // Pesquisar salas
    public List<Sala> pesquisarSalas(
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
            return salaRepository.findAll();
        }

        if (nome != null &&
                capacidade == null &&
                localizacao == null &&
                statusCodigo == null)
        {
            return salaRepository.findByNome(nome);
        }

        if (nome == null &&
                capacidade != null &&
                localizacao == null &&
                statusCodigo == null)
        {
            return salaRepository.findByCapacidade(capacidade);
        }

        if (nome == null &&
                capacidade == null &&
                localizacao != null &&
                statusCodigo == null)
        {
            return salaRepository.findByLocalizacao(localizacao);
        }

        return salaRepository.findAll()
                .stream()
                .filter(s -> nome == null ||
                        s.getNome().equalsIgnoreCase(nome))
                .filter(s -> capacidade == null ||
                        s.getCapacidade().equals(capacidade))
                .filter(s -> localizacao == null ||
                        s.getLocalizacao().equalsIgnoreCase(localizacao))
                .filter(s -> statusCodigo == null ||
                        s.getStatus().getCodigo().equals(statusCodigo))
                .toList();
    }

    // Atualizar sala
    public void atualizarSala(Sala sala)
    {
        salaRepository.save(sala);
    }
}