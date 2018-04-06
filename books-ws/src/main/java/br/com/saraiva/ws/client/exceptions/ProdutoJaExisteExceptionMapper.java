package br.com.saraiva.ws.client.exceptions;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.saraiva.commons.exception.ProdutoJaExisteException;

@Provider
public class ProdutoJaExisteExceptionMapper implements ExceptionMapper<ProdutoJaExisteException> {

	@Override
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Response toResponse(ProdutoJaExisteException exception) {
		ResponseBuilder rb = Response.status(Status.BAD_REQUEST);
		
		return rb.entity(exception.getInformacaoDoErro()).build();
	}

}
