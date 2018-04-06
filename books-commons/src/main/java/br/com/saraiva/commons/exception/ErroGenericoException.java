package br.com.saraiva.commons.exception;

public class ErroGenericoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ErroGenericoException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
