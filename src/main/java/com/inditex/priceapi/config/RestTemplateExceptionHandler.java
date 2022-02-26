package com.inditex.priceapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.priceapi.context.ApiError;
import com.inditex.priceapi.context.ErrorMessage;
import com.inditex.priceapi.controller.PricesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestTemplateExceptionHandler extends DefaultResponseErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PricesController.class);

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<?> handleConflict(IllegalArgumentException ie, HttpServletRequest request) {
        LOGGER.error("Falló en " + request.getRequestURI(), ie);

        return ResponseEntity.badRequest().body(ie.getMessage());

    }

}
