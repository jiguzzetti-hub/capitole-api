package com.inditex.priceapi.context;

public enum ErrorMessage {

	PRODUCT_ID_NOT_FOUND("No existe producto asociado al ID indicado."),
	BRAND_ID_NOT_FOUND("No existe marca asociada al ID indicado."),
	SERVICE_UNAVAILABLE_ERROR("Pruebe en unos minutos por favor.");
	
	
	

	
	
	private String errorMessage;

	ErrorMessage(String errorCode) {

		this.errorMessage = errorCode;

	}

	public String getErrorMessage() {

		return errorMessage;

	}

}
