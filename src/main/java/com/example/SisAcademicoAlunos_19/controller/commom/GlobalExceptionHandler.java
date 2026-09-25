package com.example.SisAcademicoAlunos_19.controller.commom;

import com.example.SisAcademicoAlunos_19.controller.dto.ErroCampo;
import com.example.SisAcademicoAlunos_19.controller.dto.ErroResposta;
import com.example.SisAcademicoAlunos_19.exceptions.OperacaoNaoPermitidaException;
import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import com.example.SisAcademicoAlunos_19.exceptions.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    // Erros de @NotNull, @Size, @Min, @Max, @CPFValido etc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResposta handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e)
    {
        List<ErroCampo> erros = e.getFieldErrors()
                .stream()
                .map(fieldError -> new ErroCampo(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação dos campos",
                erros
        );
    }

    // Erros de formato de data/hora
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErroResposta handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e)
    {
        String mensagem = e.getMessage();

        if (mensagem != null && mensagem.contains("LocalDate"))
        {
            return ErroResposta.unprocessableEntity("Data Inválida");
        }

        if (mensagem != null && mensagem.contains("LocalTime"))
        {
            return ErroResposta.unprocessableEntity("Hora Inválida");
        }

        return ErroResposta.unprocessableEntity(
                "Data ou hora inválida"
        );
    }

    // Validações de regra de negócio
    @ExceptionHandler(ValidacaoException.class)
    public ErroResposta handleValidacaoException(
            ValidacaoException e)
    {
        return ErroResposta.unprocessableEntity(e.getMessage());
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ErroResposta handleRegistroDuplicadoException(
            RegistroDuplicadoException e)
    {
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ErroResposta handleOperacaoNaoPermitidaException(
            OperacaoNaoPermitidaException e)
    {
        return ErroResposta.respostaPadrao(e.getMessage());
    }
}