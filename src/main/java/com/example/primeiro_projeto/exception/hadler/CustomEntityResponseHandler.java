package com.example.primeiro_projeto.exception.hadler;


import com.example.primeiro_projeto.exception.ExceptionResponse;
import com.example.primeiro_projeto.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
/*
* ExceptionResponse: uma classe criada por você para formatar a resposta de erro (data, mensagem, detalhes).

UnsupportedMathOperationException: exceção personalizada lançada na sua API.
*
* HttpStatus: enum que contém os códigos de status HTTP (200, 400, 500...).

ResponseEntity: objeto que encapsula a resposta HTTP completa, incluindo status e corpo.
*
* @ControllerAdvice: torna essa classe um “interceptador” global de erros.

@ExceptionHandler: define quais exceções o método trata.

WebRequest: contém informações sobre a requisição que gerou o erro.
* */
@ControllerAdvice /*para capturar exceções lançadas em toda a aplicação
e responder com um JSON padronizado.*/
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
