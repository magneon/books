package br.com.saraiva.ws.client.exceptions;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.saraiva.commons.exception.ErroGenericoException;

@Provider
public class ErroGenericoExceptionMapper implements ExceptionMapper<ErroGenericoException> {

	@Override
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Response toResponse(ErroGenericoException exception) {
		
		ResponseBuilder rb = Response.status(Status.SERVICE_UNAVAILABLE);
		return rb.entity(exception).build();
		
	}

}
