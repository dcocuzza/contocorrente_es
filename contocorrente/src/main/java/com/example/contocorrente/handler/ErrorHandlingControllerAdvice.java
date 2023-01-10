package com.example.contocorrente.handler;

import com.example.contocorrente.dto.GenericErrorDto;
import com.example.contocorrente.enums.ContoResponseStatus;
import com.example.contocorrente.exception.IdNotFoundException;
import com.example.contocorrente.exception.IntestatarioNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

@RestControllerAdvice
@Slf4j
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    GenericErrorDto onIdNotFoundException(IdNotFoundException e){
        log.error(e.getMessage());
        GenericErrorDto error = new GenericErrorDto();
        error.setErrorMessage(e.getMessage());
        return error;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    GenericErrorDto onEntityNotFoundException(EntityNotFoundException e){
        log.error(e.getMessage());
        GenericErrorDto error = new GenericErrorDto();
        error.setErrorMessage(e.getMessage());
        return error;
    }

    @ExceptionHandler(IntestatarioNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    GenericErrorDto onIntestatarioNotFoundException(IntestatarioNotFoundException e){
        //e.printStackTrace();
        log.error(e.getMessage());
        GenericErrorDto error = new GenericErrorDto();
        error.setErrorMessage(e.getMessage());
        return error;
    }

}
