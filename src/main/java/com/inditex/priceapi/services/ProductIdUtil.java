package com.inditex.priceapi.services;

import java.util.regex.Pattern;

public class ProductIdUtil {
	private static final int MAX_DOCUMENT_NUMBER_LENGTH = 5;
	private static final int MIN_DOCUMENT_NUMBER_LENGTH = 5;
	private static final String NUMERIC_REGEX = "^[0-9]+$";

	private ProductIdUtil() {
	}

	public static boolean validateProductId(String productId) {
		return hasValidProductIdLength(productId) && containsOnlyNumbers(productId);
	}
	
	private static boolean hasValidProductIdLength(String productId) {

		return productId.length() == MAX_DOCUMENT_NUMBER_LENGTH
				|| productId.length() == MIN_DOCUMENT_NUMBER_LENGTH;
	}

	private static boolean containsOnlyNumbers(String productId) {
		if (productId != null && productId != "") {
			return Pattern.matches(NUMERIC_REGEX, productId);
		}
		return false;
	}
}