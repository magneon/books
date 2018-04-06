package br.com.saraiva.commons.exception;

import br.com.saraiva.commons.exception.details.InformacaoDoErro;

public class ProdutoJaExisteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private InformacaoDoErro informacaoDoErro;
	
	public ProdutoJaExisteException(String message) {
		this.informacaoDoErro = new InformacaoDoErro();
		this.informacaoDoErro.setMessage(message);
	}
	
	public InformacaoDoErro getInformacaoDoErro() {
		return informacaoDoErro;
	}

}