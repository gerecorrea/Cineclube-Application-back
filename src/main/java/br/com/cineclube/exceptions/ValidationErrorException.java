package br.com.cineclube.exceptions;

public class ValidationErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationErrorException(String message, Exception e) {
		super( message, e );
	}

	public ValidationErrorException(String message) {
		super( message );
	}

}
