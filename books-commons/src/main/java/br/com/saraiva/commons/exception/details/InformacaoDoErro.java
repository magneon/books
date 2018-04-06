package br.com.saraiva.commons.exception.details;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder("message")
public class InformacaoDoErro {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}