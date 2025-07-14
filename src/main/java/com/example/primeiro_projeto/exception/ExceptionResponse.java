package com.example.primeiro_projeto.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
