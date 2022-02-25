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

	@ExceptionHandler(value = { HttpServerErrorException.class, HttpClientErrorException.class })
	protected ResponseEntity<?> handleConflict(HttpClientErrorException e, HttpServletRequest request) {
		LOGGER.error("Falló en " + request.getRequestURI(), e);
		try {
			ObjectMapper mapper = new ObjectMapper();
			return ResponseEntity.badRequest().body(mapper.readValue(e.getResponseBodyAsString(), ApiError.class));
		} catch (Exception ex) {
			LOGGER.error("Fallo en exceptionHandler: ", ex);
			return ResponseEntity.status(503).body(new ApiError(ErrorMessage.SERVICE_UNAVAILABLE_ERROR.toString(),
					ErrorMessage.SERVICE_UNAVAILABLE_ERROR.getErrorMessage()));

		}

	}

}
