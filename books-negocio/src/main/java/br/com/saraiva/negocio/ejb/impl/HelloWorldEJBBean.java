package br.com.saraiva.negocio.ejb.impl;

import javax.ejb.Stateless;

import br.com.saraiva.negocio.ejb.HelloWorldEJBService;

@Stateless
public class HelloWorldEJBBean implements HelloWorldEJBService {

	@Override
	public String teste() {
		return "Teste OK!";
	}

}
