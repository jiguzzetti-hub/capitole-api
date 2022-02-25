package com.inditex.priceapi.context;

import java.util.HashMap;
import java.util.Map;

public class ApiError {
	private String code;
	private String message;
	private Map<String, String> fieldsErrors = new HashMap<>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, String> getFieldsErrors() {
		return fieldsErrors;
	}
	public void setFieldsErrors(Map<String, String> fieldsErrors) {
		this.fieldsErrors = fieldsErrors;
	}
	
	public ApiError() {

	}
	
	public ApiError(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ApiError(String code, String message, Map<String, String> fieldsErrors) {
		super();
		this.code = code;
		this.message = message;
		this.fieldsErrors = fieldsErrors;
	}
	
	
}