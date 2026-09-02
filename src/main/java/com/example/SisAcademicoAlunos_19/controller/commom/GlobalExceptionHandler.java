package com.example.SisAcademicoAlunos_19.controller.commom;

import com.example.SisAcademicoAlunos_19.controller.dto.ErroCampo;
import com.example.SisAcademicoAlunos_19.controller.dto.ErroResposta;
import com.example.SisAcademicoAlunos_19.exceptions.OperacaoNaoPermitidaException;
import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de validação dos campos do DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResposta handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<ErroCampo> erros = e.getFieldErrors()
                .stream()
                .map(fieldError -> new ErroCampo(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação dos campos",
                erros
        );
    }

    // Trata tentativa de cadastrar um registro que já existe
    @ExceptionHandler(RegistroDuplicadoException.class)
    public ErroResposta handleRegistroDuplicadoException(
            RegistroDuplicadoException e) {

        return ErroResposta.conflito(e.getMessage());
    }
    // Trata operações que não podem ser realizadas
    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ErroResposta handleOperacaoNaoPermitidaException(
            OperacaoNaoPermitidaException e) {

        return ErroResposta.respostaPadrao(e.getMessage());
    }
}